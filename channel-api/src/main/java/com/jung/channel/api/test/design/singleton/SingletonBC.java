package com.jung.channel.api.test.design.singleton;

/**
 * Description 饿汉式双重校验锁 + volatile修饰变量生成内存屏障，防止JMM指令重排序，并发安全
 * Author yangjun
 * Date 2020/6/28 4:38 下午
 **/
public class SingletonBC {

    private static volatile SingletonBC singletonBC = null;

    private SingletonBC(){

    }

    public static SingletonBC getInstance(){
        if(null == singletonBC){
            synchronized (SingletonBC.class){
                if(null == singletonBC){
                    singletonBC = new SingletonBC();
                }
            }
        }
        return singletonBC;
    }
}
