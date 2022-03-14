package com.atech.mongodbapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("com.atech.mongodbapp.aop.AspectJExpressions.forCCTVController()")
    public void beforeAdvice(){

        log.debug("=============================================");
        log.debug("\nExecuting Before CCTV Controller Methods\n ");
        log.debug("=============================================");
    }
}
