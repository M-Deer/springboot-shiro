package com.deer.component.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MybatisPlusConfiguration
 * @Author: Mr_Deer
 * @Date: 2019/5/16 14:21
 * @Description: mp 的配置文件
 */
@Configuration
public class MybatisPlusConfiguration {

    /**
     * 格式化 SQL
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
