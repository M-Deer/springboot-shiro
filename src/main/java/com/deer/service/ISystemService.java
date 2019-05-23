package com.deer.service;

import com.deer.model.User;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @ClassName: ISystemService
 * @Author: Mr_Deer
 * @Date: 2019/5/22 11:05
 * @Description: 系统层面 service
 */
public interface ISystemService {

    /**
     * 登录认证
     *
     * @param user 用户对象
     * @return 主页路由
     * @throws AuthenticationException 认证失败异常
     */
    String login(User user) throws AuthenticationException;

    /**
     * 注销操作
     *
     * @return 登录页
     */
    String logout();
}
