package com.jung.channel.api.test.design.singleton;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * Description 反序列化、反射、克隆攻击
 * Author yangjun
 * Date 2020/6/28 5:57 下午
 **/
class SingletonEAttackTest {

    private static class SingletonEAttack implements Serializable,Cloneable{
        private static volatile SingletonEAttack singletonEAttack = null;

        private SingletonEAttack(){}

        public static SingletonEAttack getInstance(){
            if(null == singletonEAttack){
                synchronized (SingletonEAttack.class){
                    if(null == singletonEAttack){
                        singletonEAttack = new SingletonEAttack();
                    }
                }
            }
            return singletonEAttack;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    /**
     * 攻击
     */
    public static void main(String[] args) throws Exception {
        SingletonEAttack instance = SingletonEAttack.getInstance();

        System.out.println("--------> 序列化攻击");
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(instance);
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        SingletonEAttack serializable = (SingletonEAttack)oi.readObject();
        System.out.println("序列化结果：" + (instance == serializable));

        System.out.println("--------> 反射攻击");
        Constructor<SingletonEAttack> declaredConstructors = SingletonEAttack.class.getDeclaredConstructor();
        declaredConstructors.setAccessible(true);
        SingletonEAttack reflect = declaredConstructors.newInstance();
        System.out.println("反射结果：" + (instance == reflect));

        System.out.println("--------> 克隆攻击");
        SingletonEAttack clone = (SingletonEAttack)instance.clone();
        System.out.println("反射结果：" + (instance == clone));

    }


}
