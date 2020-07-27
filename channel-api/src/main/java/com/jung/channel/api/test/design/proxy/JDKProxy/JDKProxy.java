package com.jung.channel.api.test.design.proxy.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/1 5:43 下午
 **/
public class JDKProxy implements InvocationHandler {

    private Object object;

    public JDKProxy(Object object){
        this.object = object;
    }


    private void doBefore(){
        System.out.println("过去");
    }
    private void doAfter(){
        System.out.println("回来");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        method.invoke(object, args);
        doAfter();
        return object;
    }
}
