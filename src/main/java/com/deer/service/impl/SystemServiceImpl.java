package com.deer.service.impl;

import com.deer.model.User;
import com.deer.service.ISystemService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SystemServiceImpl
 * @Author: Mr_Deer
 * @Date: 2019/5/22 11:07
 * @Description: 系统层面 serviceImpl
 */
@Service
public class SystemServiceImpl implements ISystemService {

    @Override
    public String login(User user) throws AuthenticationException {
        // 1. 通过 SecurityUtils 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 2. 将用户名和密码进行装载
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            // 3. 执行登录
            subject.login(usernamePasswordToken);
            // 返回主页路由
            return "/view/main";
        } catch (UnknownAccountException e) {
            // 4. 用户名不存在
            throw new AuthenticationException("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            // 4. 密码错误
            throw new AuthenticationException("密码输入错误");
        }
    }
}
