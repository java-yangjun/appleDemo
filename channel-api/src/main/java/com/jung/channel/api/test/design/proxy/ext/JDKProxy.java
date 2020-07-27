package com.jung.channel.api.test.design.proxy.ext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/8 5:24 下午
 **/
public class JDKProxy implements InvocationHandler {

    private Object object;

    public JDKProxy(Object obj){
        this.object = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object invoke = method.invoke(object, args);
        System.out.println("after");
        return invoke;
    }
}
