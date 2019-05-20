package com.deer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deer.mapper.UserMapper;
import com.deer.model.User;
import com.deer.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User 服务实现类
 * </p>
 *
 * @author Mr_Deer
 * @since 2019-05-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {



    @Override
    public void login(User user) throws AuthenticationException {
        // 1. 通过 SecurityUtils 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 2. 将用户名和密码进行装载
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            // 3. 执行登录
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            // 4. 用户名不存在
            throw new AuthenticationException("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            // 4. 密码错误
            throw new AuthenticationException("密码输入错误");
        }
    }
}
