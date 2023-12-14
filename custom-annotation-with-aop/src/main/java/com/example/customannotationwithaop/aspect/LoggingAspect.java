package com.example.customannotationwithaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("@annotation(com.example.customannotationwithaop.annotation.Logging)")
    public void loggingAdvice(JoinPoint joinPoint) {
        log.info("this is the JoinPoint:" + joinPoint.toString());
    }
}
