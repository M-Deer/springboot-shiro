package com.deer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deer.model.User;
import org.apache.shiro.authc.AuthenticationException;

/**
 * <p>
 * User 服务类
 * </p>
 *
 * @author Mr_Deer
 * @since 2019-05-17
 */
public interface IUserService extends IService<User> {

    /**
     * 登录认证
     *
     * @param user 用户对象
     * @throws AuthenticationException 认证失败异常
     */
    void login(User user) throws AuthenticationException;
}
