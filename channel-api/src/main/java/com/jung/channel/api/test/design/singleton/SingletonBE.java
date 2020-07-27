package com.jung.channel.api.test.design.singleton;

import java.io.Serializable;

/**
 * Description 枚举，实现简单，线程安全。防止反序列化、反射攻击，并禁止克隆。最好的实现方式。
 * Author yangjun
 * Date 2020/6/28 5:07 下午
 **/
public enum  SingletonBE implements Serializable,Cloneable {

    INSTANCE;

    public void method(){
        System.out.println("");
    }

}
