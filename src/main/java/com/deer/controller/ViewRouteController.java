package com.deer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ViewRouteController
 * @Author: Mr_Deer
 * @Date: 2019/5/21 10:19
 * @Description: 页面路由 controller
 */
@Controller
@RequestMapping(path = "/view")
public class ViewRouteController {

    /**
     * 页面跳转 — 登录页/首页
     *
     * @return 视图路径
     */
    @GetMapping(path = "/login")
    public String toLogin() {
        return "login";
    }

    /**
     * 页面跳转 — 主页
     *
     * @return 视图路径
     */
    @GetMapping(path = "/main")
    public String toMain() {
        return "main";
    }

    /**
     * 页面跳转 — 欢迎页
     *
     * @return 视图路径
     */
    @GetMapping(path = "/welcome")
    public String toWelcome() {
        return "welcome";
    }

    /**
     * 页面跳转 — 用户管理页
     *
     * @return 视图路径
     */
    @GetMapping(path = "/userManager")
    public String toUserManager() {
        return "userManager";
    }

    /**
     * 页面跳转 — 部门管理页
     *
     * @return 视图路径
     */
    @GetMapping(path = "/departmentManager")
    public String toDepartmentManager() {
        return "departmentManager";
    }

    /**
     * 页面跳转 — 未授权页
     *
     * @return 视图路径
     */
    @GetMapping(path = "/unauthorized")
    public String toUnauthorized() {
        return "unauthorized";
    }
}
