package com.deer.component.mvc;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName: GlobalExceptionHandler
 * @Author: Mr_Deer
 * @Date: 2019/5/22 16:58
 * @Description: 全局统一异常处理
 */
@Slf4j
@ControllerAdvice(basePackages = "com.deer.controller")
public class GlobalExceptionHandler {

    /**
     * 捕获未授权的异常
     *
     * @return 返回未授权页面
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public Object exceptionHandler() {
        System.out.println(" = = = = = = = = = = 捕获到授权异常 = = = = = = = = = = ");
        // 跳转相应的页面
        return "unauthorized";
    }
}
