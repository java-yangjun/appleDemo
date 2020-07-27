package com.jung.channel.api.test.design.singleton;

/**
 * Description 线程不安全的饱汉式
 * Author yangjun
 * Date 2020/6/28 3:52 下午
 **/
public class SingletonB {

    private static SingletonB singletonB = null;

    private SingletonB(){

    }

    public static SingletonB getSingletonOne(){
        if(null == singletonB){
            singletonB = new SingletonB();
        }
        return singletonB;
    }
}
