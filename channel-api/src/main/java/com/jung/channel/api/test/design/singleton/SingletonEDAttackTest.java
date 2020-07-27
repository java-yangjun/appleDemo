package com.jung.channel.api.test.design.singleton;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/28 7:10 下午
 **/
public class SingletonEDAttackTest {

    /**
     * 攻击BD
     */
    /*public static void main(String[] args) throws Exception {
        SingletonBD instance = SingletonBD.getInstance();

        System.out.println("--------> 序列化攻击");
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(instance);
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        SingletonBD serializable = (SingletonBD)oi.readObject();
        System.out.println("序列化结果：" + (instance == serializable));

        System.out.println("--------> 反射攻击");
        Constructor<SingletonBD> declaredConstructors = SingletonBD.class.getDeclaredConstructor();
        declaredConstructors.setAccessible(true);
        SingletonBD reflect = declaredConstructors.newInstance();
        System.out.println("反射结果：" + (instance == reflect));

        System.out.println("--------> 克隆攻击");
        SingletonBD clone = (SingletonBD)instance.clone();
        System.out.println("反射结果：" + (instance == clone));

    }*/

    /**
     * 攻击BE
     */
    public static void main(String[] args) throws Exception {
        SingletonBE instance = SingletonBE.INSTANCE;

        System.out.println("--------> 序列化攻击");
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(instance);
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        SingletonBE serializable = (SingletonBE)oi.readObject();
        System.out.println("序列化结果：" + (instance == serializable));

        //抛出异常NoSuchMethodException----->getDeclaredConstructor
        System.out.println("--------> 反射攻击");
        Constructor<SingletonBE> declaredConstructors = SingletonBE.class.getDeclaredConstructor();
        declaredConstructors.setAccessible(true);
        SingletonBE reflect = declaredConstructors.newInstance();
        System.out.println("反射结果：" + (instance == reflect));

        //禁止克隆
        /*System.out.println("--------> 克隆攻击");
        SingletonBE clone = (SingletonBE)instance.clone();
        System.out.println("反射结果：" + (instance == clone));*/

    }
}
