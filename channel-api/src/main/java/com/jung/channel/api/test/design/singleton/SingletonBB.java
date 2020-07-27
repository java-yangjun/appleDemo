package com.jung.channel.api.test.design.singleton;

/**
 * Description 饱汉式双重校验锁，大部分情况线程安全，并发调用密集情况，
 *          会出现双重校验锁失效的情况，原因是：JMM的指令重排序优化的存在。
 *          初始化对象和将对象指给instance的指令顺序是不固定的，
 *          一个线程构造函数调用，内存开辟空间，对象变量初始化值，但还未完全初始化完成。
 *          另一个来调用，判断该单例并不是空的（实际并未完全初始化），直接拿去用了，就会出错。
 *          所以需要用到volatile来禁止指令重排序。
 *
 * Author yangjun
 * Date 2020/6/28 4:05 下午
 **/
public class SingletonBB {

    private static SingletonBB singletonBB = null;

    private SingletonBB(){}

    public static SingletonBB getInstance(){

        if(null == singletonBB){
            synchronized (SingletonBB.class){
                if(null == singletonBB){
                    singletonBB = new SingletonBB();
                }
            }
        }
        return singletonBB;
    }
}
