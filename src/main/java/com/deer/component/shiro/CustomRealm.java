package com.deer.component.shiro;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.deer.component.util.GlobalUtil;
import com.deer.mapper.UserMapper;
import com.deer.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @ClassName: CustomRealm
 * @Author: Mr_Deer
 * @Date: 2019/5/17 9:54
 * @Description: 自定义数据源
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;


    @Override
    public String getName() {
        return "CustomRealm";
    }

    /**
     * 授权
     *
     * @param principalCollection 用户信息
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(" = = = = = = = = = = = = = = = = 开始进行授权 = = = = = = = = = = = = = = = = ");
        // 1. 获取用户名
        String username = principalCollection.getPrimaryPrincipal().toString();
        // 2. 通过用户名查询所有的权限表达式
        Set<String> permissions = userMapper.getPermissionExpressionsByUsername(username);
        // 3. 返回授权用户对象，同时装载权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken 认证信息
     * @return 用户信息
     * @throws AuthenticationException 认证失败
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(" = = = = = = = = = = = = = = = = 开始进行认证 = = = = = = = = = = = = = = = = ");
        // 1. 获取用户名
        String username = authenticationToken.getPrincipal().toString();
        // 2. 现根据用户名查询是否存在该用户
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        // 3. 判断 user 是否为空
        if (GlobalUtil.isEmpty(user))
            // 为空则代表用户名输入有误
            return null;
        /*
         * 4. user 不为空，则认证密码
         * 我们只需要给当前 查询出来的用户名、查询出来的用户密码（第三个参数是我们的当前 Realm 的名字），授权器会自动帮我们进行认证
         * 同时这里返回一个 AuthenticationInfo 的子类 SimpleAuthenticationInfo
         */
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}
