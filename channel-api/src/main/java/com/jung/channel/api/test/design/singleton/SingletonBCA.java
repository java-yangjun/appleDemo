package com.jung.channel.api.test.design.singleton;

import java.io.Serializable;

/**
 * @Description 线程安全，又防止序列化，反射、克隆攻击
 * 1、防止反射
 *    定义一个全局变量，当第二次创建的时候抛出异常
 * 2、防止克隆破坏
 *    重写clone(),直接返回单例对象
 * 3、防止序列化破坏
 *    添加readResolve(),返回Object对象
 */
public class SingletonBCA implements Serializable,Cloneable{

    private static volatile SingletonBCA singletonBCA = null;
    private static volatile boolean isCreate = false;

    private SingletonBCA(){
        if(isCreate){
            throw new RuntimeException("已经实例化一次了。。。");
        } else {
            isCreate = true;
        }
    }

    public static SingletonBCA getInstance(){
        if(null == singletonBCA){
            synchronized (SingletonBCA.class){
                if(null == singletonBCA){
                    singletonBCA = new SingletonBCA();
                }
            }
        }
        return singletonBCA;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return singletonBCA;
    }

    private Object readResolve(){
        return singletonBCA;
    }
}
