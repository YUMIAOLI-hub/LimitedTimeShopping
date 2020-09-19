package com.miaoshaProject.service.impl;

import com.miaoshaProject.Dao.UserDoMapper;
import com.miaoshaProject.Dao.UserPasswordMapper;
import com.miaoshaProject.dataObject.UserDo;
import com.miaoshaProject.dataObject.UserPassword;
import com.miaoshaProject.error.BusinessException;
import com.miaoshaProject.error.EmBusinessError;
import com.miaoshaProject.service.UserService;
import com.miaoshaProject.service.model.UserModel;
import com.miaoshaProject.validator.ValidationResult;
import com.miaoshaProject.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDoMapper userDoMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        if (userDo == null) {
            return  null;
        }
        UserPassword userPassword = userPasswordMapper.selectByUserId(userDo.getId());
        return  convertFromDataObject(userDo, userPassword);
    }

    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if(userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
//        if(StringUtils.isEmpty(userModel.getName()) || userModel.getGender() == null
//        || userModel.getAge() == null || StringUtils.isEmpty(userModel.getTelphone())) {
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }
        ValidationResult result=validator.validate(userModel);
        if (result.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        //实现model-》data object(DAo层认dataobject)
        //insertSelective 如果字段为null的话，不执行插入操作，不覆盖数据库中原来的数据
        UserDo userDo = convertFromModel(userModel);
        try{
            userDoMapper.insertSelective(userDo);
        }catch (DuplicateKeyException ex) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"该手机号已被注册");
        }

        userModel.setId(userDo.getId());
        UserPassword userPassword = convertPasswordFromModel(userModel);
        userPasswordMapper.insertSelective(userPassword);
        return;

    }

    @Override
    public UserModel validateLogin(String telphone, String encryptPassword) throws BusinessException {
        //通过用户的手机获取用户信息
        UserDo userDo = userDoMapper.selectByTelphone(telphone);
        if (userDo == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPassword userPassword = userPasswordMapper.selectByUserId(userDo.getId());
        UserModel userModel = convertFromDataObject(userDo, userPassword);
        //比对用户信息内加密的密码是否和传输进来的密码相匹配
        if (!StringUtils.equals(encryptPassword, userModel.getEncryptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;

    }

    private UserPassword convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return  null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setEncryptPassword(userModel.getEncryptPassword());
        userPassword.setUserId(userModel.getId());
        return  userPassword;

    }

    private UserDo convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return  null;
        }
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userModel,userDo);
        return  userDo;
    }

    private  UserModel convertFromDataObject(UserDo userDo, UserPassword userpassword) {
        if (userDo == null) {
            return  null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDo, userModel);
        if (userpassword != null) {
            userModel.setEncryptPassword(userpassword.getEncryptPassword());
        }
        return  userModel;
    }

}
