package com.deer.service.impl;

import com.deer.service.IDepartmentManagerService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DepartmentManagerServiceImpl
 * @Author: Mr_Deer
 * @Date: 2019/5/22 14:44
 * @Description: 部门管理 serviceImpl
 */
@Service
public class DepartmentManagerServiceImpl implements IDepartmentManagerService {

    @Override
    public String getItems() {
        String result = "查询部门成功";
        System.out.println(" = = = = = = = = = = " + result + " = = = = = = = = = = ");
        return result;
    }

    @Override
    public String insertItem() {
        String result = "新增部门成功";
        System.out.println(" = = = = = = = = = = " + result + " = = = = = = = = = = ");
        return result;
    }

    @Override
    public String updateItem() {
        String result = "修改部门成功";
        System.out.println(" = = = = = = = = = = " + result + " = = = = = = = = = = ");
        return result;
    }

    @Override
    public String deleteItem() {
        String result = "删除部门成功";
        System.out.println(" = = = = = = = = = = " + result + " = = = = = = = = = = ");
        return result;
    }

}
