package com.jung.channel.api.test.aop.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description 切面处理逻辑
 * Author yangjun
 * Date 2020/7/9 11:50 上午
 **/
@Aspect
@Order(1)
@Component
public class HelloServiceAspect {

    /**
     * 切点
     * 注解参数是一个正则表达式，
     * execution(* com.example.demo.services.impl.HelloServiceImpl.printHello(…))，解释下，
     * execution：表示执行方法的时候会触发；
     * " * " ：星号表示任意返回类型的方法；
     * printHello ：被代理的方法，这里也可以不用写实现类的方法，之际写接口方法 com.example.demo.services.HelloService.printHello(…)；
     * （…）:任意的参数；
     */
    @Pointcut("execution(* com.jung.channel.api.test.aop.service.impl.HelloServiceImpl.*(..))")
    public void helloMethod(){}

    @Pointcut("execution(* com.jung.channel.api.test.aop.service.impl.NoInterfaceServiceImpl.*(..))")
    public void sayMethod(){}

    /**
     * 前置通知
     */
    @Before("helloMethod() || sayMethod()")
    public void before(){
        System.out.println("before...");
    }

    /**
     * 后置通知
     */
    @After("helloMethod() || sayMethod()")
    public void afterMethod(){
        System.out.println("after...");
    }

    /**
     * 后置正常返回通知
     */
    @AfterReturning("helloMethod()")
    public void afterReturning(){
        System.out.println("AfterReturning...");
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("helloMethod() || sayMethod()")
    public void afterThrowing(){
        System.out.println("AfterThrowing...");
    }

    /**
     * 环绕通知
     */
    @Around("sayMethod()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("around before...");
            proceedingJoinPoint.proceed();
            System.out.println("around after...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
