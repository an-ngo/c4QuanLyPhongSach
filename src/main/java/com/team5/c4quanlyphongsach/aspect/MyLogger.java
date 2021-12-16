package com.team5.c4quanlyphongsach.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyLogger {
    @AfterReturning(pointcut = "within(com.team5.c4quanlyphongsach.controller.*)", returning = "result")
    public void log(JoinPoint joinPoint, Object result){
        System.out.println("Start log");
        String classes = joinPoint.getTarget().getClass().getSimpleName();
        String method  = joinPoint.getSignature().getName();
        System.out.println(classes + " "+ method);
    }
}
