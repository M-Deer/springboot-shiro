package com.deer.controller;

import com.deer.component.util.ResponseJsonResult;
import com.deer.model.User;
import com.deer.service.ISystemService;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: SystemController
 * @Author: Mr_Deer
 * @Date: 2019/5/22 9:29
 * @Description: 系统层面 controller
 */
@RestController
@RequestMapping(path = "/system")
public class SystemController {

    private final ISystemService iSystemService;

    @Autowired
    public SystemController(ISystemService iSystemService) {
        this.iSystemService = iSystemService;
    }

    /**
     * 登录认证
     *
     * @param user 登录用户信息
     * @return json 结果
     */
    @PostMapping
    public ResponseJsonResult login(@RequestBody User user) {
        try {
            // 认证登录
            String url = iSystemService.login(user);
            // 成功返回主页 url
            return ResponseJsonResult.successResult(url);
        } catch (AuthenticationException e) {
            // 认证失败返回错误提示
            return ResponseJsonResult.unsuccessResult(e.getMessage());
        }
    }

    /**
     * 注销/退出
     */
    @GetMapping
    public String logout() {
        return iSystemService.logout();
    }
}
