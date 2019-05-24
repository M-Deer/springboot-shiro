package com.deer.component.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

    // 注入 redis 配置
    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private String redisPort;
    @Value("${redis.database}")
    private int redisDatabase;

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
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier(value = "customRealm") CustomRealm customRealm,
                                                               @Qualifier(value = "shiroCacheManager") RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 绑定 Realm
        defaultWebSecurityManager.setRealm(customRealm);

        // 装载缓存管理器
        defaultWebSecurityManager.setCacheManager(redisCacheManager);
        return defaultWebSecurityManager;
    }

    /**
     * 创建 Realm
     *
     * @param hashedCredentialsMatcher 加密器
     * @return Realm
     */
    @Bean(name = "customRealm")
    public CustomRealm customRealm(@Qualifier(value = "hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
        // 使用的是我们自定义的 Realm
        CustomRealm customRealm = new CustomRealm();
        // 装载加密器
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return customRealm;
    }

    /**
     * 设置及加密器
     *
     * @return 加密器
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //散列次数，默认1次
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;
    }

    /**
     * 配置 redis 的管理器，在这里做 redis 的相关配置
     * 中间用到都是我们配置文件中的值，注入址用
     *
     * @return redis 管理器
     */
    @Bean(value = "redisManager")
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        // 地址的格式：127.0.0.1:6379
        redisManager.setHost(redisHost + ":" + redisPort);
        // 数据库的下标，使用的具体是哪一个数据分库
        redisManager.setDatabase(redisDatabase);
        // 超时时间
        redisManager.setTimeout(0);
        return redisManager;
    }

    /**
     * 装载缓存管理器
     *
     * @param redisManager redis 管理器
     * @return redis 缓存管理器
     */
    @Bean(value = "shiroCacheManager")
    public RedisCacheManager shiroCacheManager(@Qualifier(value = "redisManager") RedisManager redisManager) {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager);
        return cacheManager;
    }
    /* = = = = = = = = = = = = = = = = = = 注解无效时的手动配置 = = = = = = = = = = = = = = = = = = */

    /**
     * 开启Shiro的注解
     *
     * @return
     */
/*    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }*/

    /**
     * 开启aop注解支持
     *
     * @param defaultWebSecurityManager
     * @return
     */
/*    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier(value = "defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }*/
}
