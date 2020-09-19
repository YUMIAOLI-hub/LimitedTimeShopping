package com.miaoshaProject.service.impl;

import com.miaoshaProject.Dao.OrderDoMapper;
import com.miaoshaProject.Dao.SequenceDOMapper;
import com.miaoshaProject.dataObject.OrderDo;
import com.miaoshaProject.dataObject.SequenceDO;
import com.miaoshaProject.error.BusinessException;
import com.miaoshaProject.error.EmBusinessError;
import com.miaoshaProject.service.ItemService;
import com.miaoshaProject.service.OrderService;
import com.miaoshaProject.service.UserService;
import com.miaoshaProject.service.model.ItemModel;
import com.miaoshaProject.service.model.OrderModel;
import com.miaoshaProject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderDoMapper orderDoMapper;
    @Autowired
    private SequenceDOMapper sequenceDOMapper;
    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException {
        //校验下单状态，下单商品是否存在，用户是否合法，购买数量是否正确
        ItemModel itemModel=itemService.getItemById(itemId);
        if (itemModel == null) {
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if(userModel == null) {
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户信息不存在");
        }
        if (amount <= 0 || amount > 99) {
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"数量信息不正确");
        }
        //校验活动信息
        if(promoId!=null) {
            //校验该活动是否适用于该商品
            if (promoId.intValue()!= itemModel.getPromoModel().getId()) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动信息不正确");
            }else if (itemModel.getPromoModel().getStatus().intValue() != 2) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动未开始");
            }

        }

        //下单成功后减库存，（支付减库存，商家需要承担超卖的风险）保证用户体验
        boolean result = itemService.decreaseStock(itemId,amount);
        if(!result) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        //订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        orderModel.setPromoId(promoId);
        if (promoId != null) {
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());
        }

        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));
        //生成交易流水号（订单号）
        orderModel.setId(generateOrderNo());
        OrderDo orderDo = convertFromOrderModel(orderModel);
        orderDoMapper.insertSelective(orderDo);
        //加上商品的销量
        itemService.increaseSales(itemId,amount);

        //返回前端
        return orderModel;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String generateOrderNo() {
        //订单号16位，
        StringBuilder stringBuilder = new StringBuilder();
        // 前八位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);
        //中间六位为自增序列
        //获取当前sequence
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6- sequenceStr.length();i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);

        //最后两位为分库分表位,暂时写死
        stringBuilder.append("00");
        return  stringBuilder.toString();
    }
    private OrderDo convertFromOrderModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDo orderDo = new OrderDo();
        BeanUtils.copyProperties(orderModel,orderDo);
        return orderDo;
    }
}
