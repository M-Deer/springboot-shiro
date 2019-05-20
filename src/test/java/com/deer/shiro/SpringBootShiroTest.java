package com.deer.shiro;

import com.deer.SpringbootShiroApplicationTests;
import com.deer.model.User;
import com.deer.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
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
    private IUserService iUserService;
    @Autowired
    private DefaultWebSecurityManager defaultWebSecurityManager;

    /**
     * 登录认证测试
     */
    @Test
    public void loginTest() {
        // 注意：使用 junit 测试，并不处于同一根线程中
        ThreadContext.bind(defaultWebSecurityManager);

        // 创建对象
        User user = new User();
        user.setUsername("Tom");
        user.setPassword("1aaa2a3a");
        try {
            // 登录测试
            iUserService.login(user);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            // 打印错误信息
            System.out.println(e.getMessage());
        }
    }
}
