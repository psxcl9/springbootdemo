package com.liaocx.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Web层切面日志
 *
 * @author liaocx
 * @date 2018/10/31
 */
@Aspect
@Order(0)
@Component
public class WebLogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);


    @Pointcut("execution(public * com.liaocx.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            System.out.println("\r\n");
            logger.info("地址 : " + request.getRequestURL().toString());
            logger.info("请求方式 : " + request.getMethod());
            logger.info("IP : " + request.getRemoteAddr());
            logger.info("执行的方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            Object[] args = joinPoint.getArgs().clone();
            logger.info("参数 : " + Arrays.toString(args));
        }
    }

    /**
     * 处理完请求，返回内容和花费时间
     * 要有返回值才会执行这个方法, 只抛出异常不会执行
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        logger.info("返回内容 : " + ret);
        logger.info("花费时间 : " + (System.currentTimeMillis() - startTime.get()) + "毫秒");
    }
}
