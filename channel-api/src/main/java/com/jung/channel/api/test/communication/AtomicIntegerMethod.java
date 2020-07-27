package com.jung.channel.api.test.communication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description 原因是AtomicInteger的value是用volatile修饰的
 * Author yangjun
 * Date 2020/6/19 4:21 下午
 **/
public class AtomicIntegerMethod {

    static AtomicInteger at = new AtomicInteger(1);

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                while (at.intValue() != 1){
                }
                System.out.println(i);
                at.set(2);
            }
        }, "t1").start();

        new Thread(() ->{
            for (int i = 1; i < 27; i++) {
                while (at.intValue() == 1){
                }
                char charStr = (char) (i+64);
                System.out.println(charStr);
                at.set(1);
            }
        }, "t2").start();

    }
}
