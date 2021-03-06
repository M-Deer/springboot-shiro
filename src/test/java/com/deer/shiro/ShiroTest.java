package com.deer.shiro;

import com.deer.component.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    /**
     * Shiro 使用 ini 文件做授权测试（角色）
     * <p>
     * 注意：做授权测试的前提一定是认证通过，否则不能进行授权测试
     */
    @Test
    public void hasRoleTest() {
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
        String username = "userB";
        String password = "222";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 7. 登录认证
        subject.login(token);
        // 8. 判断用户是否拥有单个角色(true: 拥有该角色/ false：没有该角色)
//        boolean flag = subject.hasRole("role1");
        // 8. 判断用户是否拥有全部角色(true：拥有全部角色/ false：不满足全部角色)
        List<String> list = Arrays.asList("role1", "role2", "role3");
//        boolean flag = subject.hasAllRoles(list);
        // 8. 判断用户匹配哪个角色，返回一个 boolean 集合，匹配成功的返回 true，失败的返回 false
        boolean[] booleans = subject.hasRoles(list);

        System.out.println("是否包含该角色 = = = = = = = = = " + Arrays.toString(booleans));

        // 8. 通过 checkRoles() 方法来进行匹配。匹配成功不做任何操作，有一个匹配失败则直接抛出 UnauthorizedException 异常
        subject.checkRoles(list);
    }

    /**
     * Shiro 使用 ini 文件做授权测试（权限）
     * <p>
     * 注意：做授权测试的前提一定是认证通过，否则不能进行授权测试
     */
    @Test
    public void permitTest() {
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
        String username = "userA";
        String password = "111";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 7. 登录认证
        subject.login(token);
        // 8. 判断用户是否拥有单个权限(true: 拥有该权限/ false：没有该权限)
//        boolean flag = subject.isPermitted("user:create");
//        boolean flag = subject.isPermitted("department:create");
//        System.out.println("是否拥有该权限 = = = = = = = = = "+flag);

        // 8. 判断用户是否拥有全部权限(true: 拥有全部权限/ false：其中有一个不满足)
//        boolean flag = subject.isPermittedAll("user:create", "user:update");
//        boolean flag = subject.isPermittedAll("user:create", "user:update","department:create");
//        System.out.println("是否拥有全部权限 = = = = = = = = = "+flag);

        // 8. 判断用户是否拥有部分权限(true: 拥有该权限/ false：没有该群贤)
        boolean[] flag = subject.isPermitted("user:create", "user:update", "department:create");
        System.out.println("部分结果 = = = = = = = = = " + Arrays.toString(flag));

        // 8. 判断用户是否拥有单个权限，拥有则没有任何操作，不拥有则抛出 UnauthorizedException 异常
        subject.checkRole("department:create");
    }

    /**
     * 测试自定义 Realm
     */
    @Test
    public void customRealmTest() {
        // 1. 获取默认的管理管理器 SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 2. 创建一个 Realm，同时把我们写好的 ini 文件路径放进来
        CustomRealm customRealm = new CustomRealm();
        // 3. 设置 Realm
        defaultSecurityManager.setRealm(customRealm);
        // 4. 将 SecurityManager 绑定到当前环境中，注意这里的 SecurityUtils 引用的是：org.apache.shiro.SecurityUtils 不要引用错误了
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 5. 创建当前登陆的主体
        Subject subject = SecurityUtils.getSubject();
        // 6. 创建主体登录所需要的认证信息对象 token。也就是我们的用户名和密码
        String username = "userA";
        String password = "111";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 7. 登录认证
        subject.login(token);
    }

    /**
     * MD5 加密测试
     */
    @Test
    public void md5Test() {
        String salt = "Amy";
        /*
         * 第一个参数是 明文
         * 第二个参数是 盐
         * 第三个参数是 散列次数
         */
        Md5Hash md5Hash = new Md5Hash("123", null, 1);
        Md5Hash md5Salt = new Md5Hash("123", salt, 1);
        System.out.println("加盐前 = = = = = = = " + md5Hash);
        System.out.println("加盐后 = = = = = = = " + md5Salt);
    }
}
