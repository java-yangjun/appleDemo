package com.jung.channel.api.test.design.singleton;

import java.io.Serializable;

/**
 * Description 依赖jvm保证线程安全。SingletonBD加载时，内部类不会实例化，只有当getInstance被调用时，内部类才会进行初始化
 * //原因：虚拟机会保证一个类的构造器<clinit>()方法在多线程环境中被正确地加载，同步，如果多个线程同时去初始化一个类，那么只有一个线程去执行这个类的
 * //构造器<clinit>()方法，其他线程都需要阻塞等待，直到活动线程执行<clinit>（）方法完毕。
 * Author yangjun
 * Date 2020/6/28 4:55 下午
 **/
public class SingletonBD implements Serializable,Cloneable {

    private SingletonBD(){}

    private static class SingletonInstance{
        private static final SingletonBD instance = new SingletonBD();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static SingletonBD getInstance(){
        return SingletonInstance.instance;
    }

}
