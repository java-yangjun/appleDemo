package com.jung.channel.api.test.design.singleton;

/**
 * Description 饱汉式加方法锁，线程安全，但是多次创建synchronize性能损耗大，效率不高
 * Author yangjun
 * Date 2020/6/28 4:10 下午
 **/
public class SingletonBA {

    private static SingletonBA singletonBA = null;

    private SingletonBA(){}

    public static synchronized SingletonBA getInstance(){

        if(null == singletonBA){
            singletonBA = new SingletonBA();
        }
        return singletonBA;
    }
}
