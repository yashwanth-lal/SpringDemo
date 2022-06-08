package com.app.netflixdemoapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class MovieServiceAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.app.netflixdemoapp.restController.*.*(..))")
    private void forControllerPackage(){
        //this is pointcut
    }

    @Pointcut("execution(* com.app.netflixdemoapp.service.*.*(..))")
    private void forServicePackage(){
        //this is pointcut
    }

    @Pointcut("execution(* com.app.netflixdemoapp.dao.*.*(..))")
    private void forDaoPackage(){
        //this is pointcut
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    public void forAppFlow(){
        //this is pointcut
    }

    @Before("forAppFlow()")
    public void beforeC(JoinPoint jp){
        String method = jp.getSignature().toShortString();
        myLogger.fine("=====>> in @Before: calling method: "+method);

        Object[] args = jp.getArgs();

        for(Object tempArg : args){
            myLogger.fine("=====>> argument: "+tempArg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint jp, Object theResult){
        String method = jp.getSignature().toShortString();
        myLogger.fine("=====>> in @AfterReturning: from method: "+method);
        myLogger.fine("=====>> result: "+theResult);
    }

}

