package com.deer.component.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: GlobalShiroConfiguration
 * @Author: Mr_Deer
 * @Date: 2019/5/17 16:32
 * @Description:
 */
//@Configuration
public class GlobalShiroConfiguration {

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
        return shiroFilterFactoryBean;
    }

    /**
     * 创建 WebSecurityManager
     *
     * @return WebSecurityManager
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier(value = "customRealm") CustomRealm customRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 设置 Realm
        defaultWebSecurityManager.setRealm(customRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建 Realm
     *
     * @return Realm
     */
    @Bean(name = "customRealm")
    public CustomRealm globalShiroRealm() {
        return new CustomRealm();
    }

}
