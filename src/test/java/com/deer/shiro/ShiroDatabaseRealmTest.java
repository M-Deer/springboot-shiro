package com.deer.shiro;

import com.deer.SpringbootShiroApplicationTests;
import com.deer.component.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @ClassName: ShiroDatabaseRealmTest
 * @Author: Mr_Deer
 * @Date: 2019/5/17 16:12
 * @Description: 自定义Realm 测试
 */
public class ShiroDatabaseRealmTest extends SpringbootShiroApplicationTests {

    /**
     * 登录认证测试
     */
    @Test
    public void loginTest() {
        // 1. 获取默认的管理管理器 SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        CustomRealm customRealm = new CustomRealm();
        // 3. 设置 Realm
        defaultSecurityManager.setRealm(customRealm);
        // 4. 将 SecurityManager 绑定到当前环境中，注意这里的 SecurityUtils 引用的是：org.apache.shiro.SecurityUtils 不要引用错误了
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject = SecurityUtils.getSubject();
        // 6. 创建主体登录所需要的认证信息对象 token。也就是我们的用户名和密码
        String username = "Jerry";
        String password = "123";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 7. 登录认证，进行异常的捕捉，给出友好提示
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("用户名输入错误");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码输入错误");
        }
    }
}
