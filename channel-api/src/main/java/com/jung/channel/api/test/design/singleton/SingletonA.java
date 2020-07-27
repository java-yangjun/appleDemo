package com.jung.channel.api.test.design.singleton;

/**
 * Description 饿汉式：线程安全，不会出现空对象，但是浪费内存
 * Author yangjun
 * Date 2020/6/28 3:56 下午
 **/
public class SingletonA {

    private static SingletonA singletonA = new SingletonA();

    private SingletonA(){

    }

    public static SingletonA getSingletonA(){
        return singletonA;
    }
}
