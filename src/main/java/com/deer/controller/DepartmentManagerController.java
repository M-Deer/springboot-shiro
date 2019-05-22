package com.deer.controller;

import com.deer.service.IDepartmentManagerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: DepartmentManagerController
 * @Author: Mr_Deer
 * @Date: 2019/5/22 14:29
 * @Description: 部门管理 controller
 */
@RestController
@RequestMapping(path = "/departmentManager")
public class DepartmentManagerController {

    private final IDepartmentManagerService iDepartmentManagerService;

    @Autowired
    public DepartmentManagerController(IDepartmentManagerService iDepartmentManagerService) {
        this.iDepartmentManagerService = iDepartmentManagerService;
    }

    /**
     * 查询部门
     *
     * @return 查询结果
     */
    @GetMapping
    @RequiresPermissions(value = "department:select")
    public String getItems() {
        return iDepartmentManagerService.getItems();
    }

    /**
     * 新增部门
     *
     * @return 新增结果
     */
    @PostMapping
    @RequiresPermissions(value = "department:insert")
    public String insertItem() {
        return iDepartmentManagerService.insertItem();
    }

    /**
     * 修改部门
     *
     * @return 修改结果
     */
    @PutMapping
    @RequiresPermissions(value = "department:update")
    public String updateItem() {
        return iDepartmentManagerService.updateItem();
    }

    /**
     * 删除部门
     *
     * @return 删除结果
     */
    @DeleteMapping
    @RequiresPermissions(value = "department:delete")
    public String deleteItem() {
        return iDepartmentManagerService.deleteItem();
    }
}
