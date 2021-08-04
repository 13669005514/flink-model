package com.supermap.model.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author : zhangfx 2020/1/15 20:05
 * @version : 1.0
 */
@Slf4j
@Component
@Aspect
public class LogAop {

    @Before("within(com.supermap.model.controller.*)")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("********************************************");
        log.info("ip：{}", request.getRemoteAddr());
        log.info("请求url地址：{}",request.getRequestURI());
        log.info("请求方式：{}",request.getMethod());
        log.info("类名：{}",method.getDeclaringClass());
        log.info("方法名：{}",method.getName());
        for (Object arg : args) {
            if (arg instanceof MultipartFile || arg instanceof ServletRequest
                    || arg instanceof ServletResponse || arg instanceof BeanPropertyBindingResult) {
                continue;
            }
            if ("POST".equals(request.getMethod())) {
                log.info("POST方式请求参数：{}", JSONObject.toJSON(arg, SerializeConfig.globalInstance));
            } else {
                log.info("GET方式请求参数：{}",request.getQueryString());
            }
        }
        log.info("********************************************");
    }


}
