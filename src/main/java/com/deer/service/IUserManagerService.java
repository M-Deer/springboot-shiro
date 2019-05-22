package com.deer.service;

/**
 * @ClassName: IUserManagerService
 * @Author: Mr_Deer
 * @Date: 2019/5/22 14:31
 * @Description: 用户管理 service
 */
public interface IUserManagerService {

    /**
     * 查询用户
     *
     * @return 查询结果
     */
    String getItems();

    /**
     * 新增用户
     *
     * @return 新增结果
     */
    String insertItem();

    /**
     * 修改用户
     *
     * @return 修改结果
     */
    String updateItem();

    /**
     * 删除用户
     *
     * @return 删除结果
     */
    String deleteItem();
}
