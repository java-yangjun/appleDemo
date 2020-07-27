package com.jung.channel.api.test.design.singleton;

/**
 * Description 饿汉式变种：线程安全，程序启动时，初始化时就先创建对象，就算不用，也只浪费一次。
 * Author yangjun
 * Date 2020/6/28 4:02 下午
 **/
public class SingletonAB {

    private static SingletonAB singletonAB = null;

    static {
        singletonAB = new SingletonAB();
    }

    private SingletonAB(){}

    public static SingletonAB getSingletonAB(){
        return singletonAB;
    }

}
