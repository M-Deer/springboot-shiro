package com.deer.controller;

import com.deer.service.IUserManagerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UserManagerController
 * @Author: Mr_Deer
 * @Date: 2019/5/22 14:12
 * @Description: 用户管理 controller
 */
@RestController
@RequestMapping(path = "/userManager")
public class UserManagerController {

    private final IUserManagerService iUserManagerService;

    @Autowired
    public UserManagerController(IUserManagerService iUserManagerService) {
        this.iUserManagerService = iUserManagerService;
    }

    /**
     * 查询用户
     *
     * @return 查询结果
     */
    @GetMapping
    @RequiresPermissions(value = "user:select")
    public String getItems() {
        return iUserManagerService.getItems();
    }

    /**
     * 新增用户
     *
     * @return 新增结果
     */
    @PostMapping
    @RequiresPermissions(value = "user:insert")
    public String insertItem() {
        return iUserManagerService.insertItem();
    }

    /**
     * 修改用户
     *
     * @return 修改结果
     */
    @PutMapping
    @RequiresPermissions(value = "user:update")
    public String updateItem() {
        return iUserManagerService.updateItem();
    }

    /**
     * 删除用户
     *
     * @return 删除结果
     */
    @DeleteMapping
    @RequiresPermissions(value = "user:delete")
    public String deleteItem() {
        return iUserManagerService.deleteItem();
    }
}
