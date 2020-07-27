package com.jung.channel.api.test.design.proxy.ext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/8 5:27 下午
 **/
public class JKDProxyTest {

    public static void main(String[] args) {
        Apple apple = new Apple();
        Class<? extends Apple> aClass = apple.getClass();
        InvocationHandler invocationHandler = new JDKProxy(apple);

        Fruits fruits = (Fruits)Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), invocationHandler);
        fruits.sayName();
    }
}
