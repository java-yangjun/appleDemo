package com.jung.channel.api.test.design.proxy.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/1 7:36 下午
 **/
public class CglibProxy implements MethodInterceptor {

    private Object object;

    public CglibProxy(Object object){
        this.object = object;
    }

    private void doBefore(){
        System.out.println("过去");
    }
    private void doAfter(){
        System.out.println("回来");
    }

    public Object getProxy(){
        //1.创建工具类Enhancer
        Enhancer enhancer = new Enhancer();
        //2.被代理类作为父类
        enhancer.setSuperclass(object.getClass());
        //3.设置回调函数
        enhancer.setCallback(this);
        //4.新创建子类返回
        Object son = enhancer.create();
        return son;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        doBefore();
        Object o1 = methodProxy.invokeSuper(o, objects);
        doAfter();
        return o1;
    }
}
