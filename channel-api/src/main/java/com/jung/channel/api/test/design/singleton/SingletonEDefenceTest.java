package com.jung.channel.api.test.design.singleton;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * Description 反序列化、反射、克隆防守

 * Author yangjun
 * Date 2020/6/28 5:57 下午
 **/
class SingletonEDefenceTest {


    /**
     * 1、防止反射
     *    定义一个全局变量，当第二次创建的时候抛出异常
     * 2、防止克隆破坏
     *    重写clone(),直接返回单例对象
     * 3、防止序列化破坏
     *    添加readResolve(),返回单例对象
     */
    private static class SingletonEDefence implements Serializable,Cloneable{

        private static volatile SingletonEDefence singletonEDefence = null;
        private static volatile boolean isCreate = false;

        private SingletonEDefence(){
            if(isCreate){
                throw new RuntimeException("已经实例化一次了。。。");
            } else {
                isCreate = true;
            }
        }

        public static SingletonEDefence getInstance(){
            if(null == singletonEDefence){
                synchronized (SingletonEDefence.class){
                    if(null == singletonEDefence){
                        singletonEDefence = new SingletonEDefence();
                    }
                }
            }
            return singletonEDefence;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return singletonEDefence;
        }

        private Object readResolve(){
            return singletonEDefence;
        }
    }

    /**
     * 攻击
     */
    public static void main(String[] args) throws Exception {
        SingletonEDefence instance = SingletonEDefence.getInstance();

        System.out.println("--------> 序列化攻击");
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(instance);
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        SingletonEDefence serializable = (SingletonEDefence)oi.readObject();
        System.out.println("序列化结果：" + (instance == serializable));

        System.out.println("--------> 克隆攻击");
        SingletonEDefence clone = (SingletonEDefence)instance.clone();
        System.out.println("克隆结果：" + (instance == clone));

        System.out.println("--------> 反射攻击");
        Constructor<SingletonEDefence> declaredConstructors = SingletonEDefence.class.getDeclaredConstructor();
        declaredConstructors.setAccessible(true);
        SingletonEDefence reflect = declaredConstructors.newInstance();
        System.out.println("反射结果：" + (instance == reflect));

    }


}
