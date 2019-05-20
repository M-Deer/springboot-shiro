package com.deer.component.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @ClassName: GlobalShiroConfiguration
 * @Author: Mr_Deer
 * @Date: 2019/5/20 10:39
 * @Description: Shiro 整合配置
 */
@Configuration
public class GlobalShiroConfiguration {

    /**
     * 创建 ShiroFilterFactoryBean
     *
     * @param defaultWebSecurityManager 安全管理器
     * @return 过滤器
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier(value = "securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>(16);
        filterMap.put("/", "anon");
        filterMap.put("/system", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/**", "authc");
        // 设置拦截器 Map
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建 WebSecurityManager
     *
     * @return WebSecurityManager
     */
    @Bean(name = "securityManager")
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
