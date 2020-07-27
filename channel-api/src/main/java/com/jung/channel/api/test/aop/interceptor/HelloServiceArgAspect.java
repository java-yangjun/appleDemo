package com.jung.channel.api.test.aop.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description 切面处理逻辑带参数
 * Author yangjun
 * Date 2020/7/9 11:50 上午
 **/
@Aspect
@Order(2)
@Component
public class HelloServiceArgAspect {

    /**
     * 切点
     * 注解参数是一个正则表达式，
     * execution(* com.example.demo.services.impl.HelloServiceImpl.printHello(…))，解释下，
     * execution：表示执行方法的时候会触发；
     * " * " ：星号表示任意返回类型的方法；
     * printHello ：被代理的方法，这里也可以不用写实现类的方法，之际写接口方法 com.example.demo.services.HelloService.printHello(…)；
     * （…）:任意的参数；
     */

    @Pointcut("execution(* com.jung.channel.api.test.aop.service.impl.NoInterfaceServiceImpl.*(..)) && args(str)")
    public void sayMethod(String str){}

    /**
     * 前置通知
     */
    @Before(value = "sayMethod(str)", argNames = "str")
    public void before(String str){
        System.out.println("2before..." + str);
    }

    /**
     * 后置通知
     */
    @After(value = "sayMethod(str)", argNames = "str")
    public void afterMethod(String str){
        System.out.println("2after..." + str);
    }

    /**
     * 后置正常返回通知
     */
    @AfterReturning(value = "sayMethod(str)", argNames = "str")
    public void afterReturning(String str){
        System.out.println("2AfterReturning..." + str);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(value = "sayMethod(str)", argNames = "str")
    public void afterThrowing(String str){
        System.out.println("2AfterThrowing..." + str);
    }

    /**
     * 环绕通知
     */
    @Around(value = "sayMethod(str)", argNames = "proceedingJoinPoint,str")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, String str) {
        try {
            System.out.println("2around before..." + str);
            proceedingJoinPoint.proceed();
            System.out.println("2around after..." + str);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
