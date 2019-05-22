package com.deer.component.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: GlobalShiroConfiguration
 * @Author: Mr_Deer
 * @Date: 2019/5/20 10:39
 * @Description: Shiro 整合配置
 */
@Configuration
public class GlobalShiroConfiguration {

    // 视图前缀
    private static final String VIEW_PREFIX = "/view";

    /**
     * 创建 ShiroFilterFactoryBean
     *
     * @param defaultWebSecurityManager 安全管理器
     * @return 过滤器
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier(value = "defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 设置默认登陆页面
        shiroFilterFactoryBean.setLoginUrl(VIEW_PREFIX + "/login");

        // 设置拦截器 Map
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap());

        return shiroFilterFactoryBean;
    }

    /**
     * Shiro 中的拦截器中有很多常用的拦截器
     * <p>
     * anon：    无需认证直接访问
     * authc：   必须通过认证才能访问
     * user：    必须当前用户登录状态下才可访问，rememberMe 功能可直接访问
     * perms：   必须拥有该资源的权限才可访问
     * role：    必须拥有该角色的权限才可访问
     *
     * @return 拦截器 map
     */
    private Map<String, String> filterMap() {
        /*
         * 返回的 map
         * 因为要保证 filter 的顺序，所以使用的是 LinkedHashMap
         *
         * 允许匿名访问的资源放在最前面
         * 接着是一些特殊的拦截器
         * 最后是所有的 URL 都需要进行认证之后才能访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>(16);
        // 静态资源放行
        filterMap.put("/static/**", "anon");
        // 登录页 URL 放行
        filterMap.put(VIEW_PREFIX + "/login", "anon");
        // 提交登录认证的 URL 放行
        filterMap.put("/system", "anon");
        // 其他的全部必须全部通过认证后才能访问
        filterMap.put("/**", "authc");
        return filterMap;
    }

    /**
     * 创建 WebSecurityManager
     *
     * @return WebSecurityManager
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier(value = "customRealm") CustomRealm customRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 绑定 Realm
        defaultWebSecurityManager.setRealm(customRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建 Realm
     *
     * @return Realm
     */
    @Bean(name = "customRealm")
    public CustomRealm customRealm() {
        // 使用的是我们自定义的 Realm
        return new CustomRealm();
    }
}
