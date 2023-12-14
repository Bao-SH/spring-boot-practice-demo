package com.example.customannotationwithaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomAspect {

    @Before("@annotation(com.example.customannotationwithaop.annotation.Logging)")
    public void loggingAdvice(JoinPoint joinPoint) {
        log.info("this is the JoinPoint:" + joinPoint.toString());
    }

    @Pointcut("execution(public String com.example.customannotationwithaop.service.HiService.serve())")
    public void pointCut() {}

    @Before("pointCut()")
    public void beforePointCut() {
        log.info("-----before pointCut-----");
    }
}
