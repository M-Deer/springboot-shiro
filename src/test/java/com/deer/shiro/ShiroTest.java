package com.deer.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @ClassName: ShiroTest
 * @Author: Mr_Deer
 * @Date: 2019/5/10 16:10
 * @Description: shiro 测试类
 */

public class ShiroTest {

    /**
     * Shiro 使用 ini 文件做认证测试
     */
    @Test
    public void loginTest() {
        // 1. 获取默认的管理管理器 SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 2. 创建一个 Realm，同时把我们写好的 ini 文件路径放进来
        IniRealm iniRealm = new IniRealm("classpath:shiro/shiro.ini");
        // 3. 设置 Realm
        defaultSecurityManager.setRealm(iniRealm);
        // 4. 将 SecurityManager 绑定到当前环境中，注意这里的 SecurityUtils 引用的是：org.apache.shiro.SecurityUtils 不要引用错误了
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 5. 创建当前登陆的主体
        Subject subject = SecurityUtils.getSubject();
        // 6. 创建主体登录所需要的认证信息对象 token。也就是我们的用户名和密码
        String username = "admin";
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
        // 8. 判断是否登陆成功，成功返回true，失败返回 false
        System.out.println("登录是否成功 = = = = =" + subject.isAuthenticated());
        // 9. 注销登录
        subject.logout();
        // 10. 再次检测是否登录成功
        System.out.println("是否还在登录状态 = = = = =" + subject.isAuthenticated());
    }
}
