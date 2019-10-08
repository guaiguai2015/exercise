package com.wrl.exercise.aopModel;


import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-26 14:57
 */

@Aspect
public class TestAspect {

    @Pointcut("execution(* onClick(..))")
//    @Pointcut("execution(@com.wrl.exercise.aopModel.SingleClick * *(..))") //自定义注解的方式
    public void clickMethod(){

    }

    @Before("clickMethod()")
    public void before(JoinPoint point){
        Log.e("wrl","Before");
    }

    @Around("clickMethod()")
    public void arround(ProceedingJoinPoint joinPoint) throws Throwable{
        Log.e("wrl","arroundas");
        joinPoint.proceed();
    }

    @After("clickMethod()")
    public void after(JoinPoint point){
        Log.e("wrl","@After");

    }

    @AfterReturning("clickMethod()")
    public void afterReturning(JoinPoint point,Object returnValue){
        Log.e("wrl","@AfterReturning");

    }

    @AfterThrowing("clickMethod()")
    public void afterThrowing(Throwable ex){
        Log.e("wrl","@AfterThrowing");
    }
}
