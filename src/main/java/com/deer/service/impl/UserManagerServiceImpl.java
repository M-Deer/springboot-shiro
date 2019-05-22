package com.deer.service.impl;

import com.deer.service.IUserManagerService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserManagerServiceImpl
 * @Author: Mr_Deer
 * @Date: 2019/5/22 14:37
 * @Description: 用户管理 serviceImpl
 */
@Service
public class UserManagerServiceImpl implements IUserManagerService {

    @Override
    public String getItems() {
        String result = "查询用户成功";
        System.out.println(" = = = = = = = = = = " + result + " = = = = = = = = = = ");
        return result;
    }

    @Override
    public String insertItem() {
        String result = "新增用户成功";
        System.out.println(" = = = = = = = = = = " + result + " = = = = = = = = = = ");
        return result;
    }

    @Override
    public String updateItem() {
        String result = "修改用户成功";
        System.out.println(" = = = = = = = = = = " + result + " = = = = = = = = = = ");
        return result;
    }

    @Override
    public String deleteItem() {
        String result = "删除用户成功";
        System.out.println(" = = = = = = = = = = " + result + " = = = = = = = = = = ");
        return result;
    }
}
