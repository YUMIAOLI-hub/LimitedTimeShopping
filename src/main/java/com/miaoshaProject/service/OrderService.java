package com.miaoshaProject.service;

import com.miaoshaProject.error.BusinessException;
import com.miaoshaProject.service.model.OrderModel;

public interface OrderService {
    //1。通过前段url上传过来的秒杀活动id，然后下单接口内校验对应id是否属于对应商品且活动已开始
    //（考虑实际业务，一个商品可能在多个app上进行秒杀活动，判断是哪一个接口进入的秒杀活动）
    //2。直接在下单接口内判断对应的商品是否存在秒杀活动，若存在进行中的则以秒杀价格下单（平价商品也要检验，所以性能下降）
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;
}
