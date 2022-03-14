package com.atech.mongodbapp.aop;

import org.aspectj.lang.annotation.Pointcut;

public class AspectJExpressions {

    @Pointcut("execution (* com.atech.mongodbapp.controllers.CCTVController.*(..))")
    public void forCCTVController(){}
}
