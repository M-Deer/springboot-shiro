package com.deer.service;

/**
 * @ClassName: IDepartmentManagerService
 * @Author: Mr_Deer
 * @Date: 2019/5/22 14:42
 * @Description: 部门管理 service
 */
public interface IDepartmentManagerService {

    /**
     * 查询部门
     *
     * @return 查询结果
     */
    String getItems();

    /**
     * 新增部门
     *
     * @return 新增结果
     */
    String insertItem();

    /**
     * 修改部门
     *
     * @return 修改结果
     */
    String updateItem();

    /**
     * 删除部门
     *
     * @return 删除结果
     */
    String deleteItem();
}
