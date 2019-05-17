package com.deer;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan(basePackages = {"com.baomidou.mybatisplus.samples.quickstart.mapper", "com.deer.mapper"})
@SpringBootApplication
public class SpringbootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroApplication.class, args);
        log.info(" = = = = = = = = = = = = = = = = spring boot start success = = = = = = = = = = = = = = = = ");
    }

}
