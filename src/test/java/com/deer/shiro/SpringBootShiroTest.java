package com.deer.shiro;

import com.deer.SpringbootShiroApplicationTests;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: SpringBootShiroTest
 * @Author: Mr_Deer
 * @Date: 2019/5/17 16:12
 * @Description: 整合 Spring boot 后的测试类
 */
public class SpringBootShiroTest extends SpringbootShiroApplicationTests {

    @Autowired
    private DefaultWebSecurityManager defaultWebSecurityManager;

    /**
     * 登录认证测试
     */
    @Test
    public void loginTest() {
        // 注意：使用 junit 测试，并不处于同一根线程中
        ThreadContext.bind(defaultWebSecurityManager);

        String username = "Tom";
        String password = "1a2a3";
        // 1. 通过 SecurityUtils 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 2. 将用户名和密码进行装载
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            // 3. 执行登录
            subject.login(usernamePasswordToken);
            System.out.println("登录认证成功");
        } catch (UnknownAccountException e) {
            // 4. 用户名不存在
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            // 4. 密码错误
            System.out.println("密码输入错误");
        }
    }
}
