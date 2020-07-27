package com.jung.channel.api.test.communication;

/**
 * Description Object的wait() notify()
 * Author yangjun
 * Date 2020/6/19 4:21 下午
 **/
public class WaitNotifyMethod{


    public static void main(String[] args) {

        Object o = new Object();

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                synchronized (o){
                    System.out.println(i);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        o.notifyAll();
                    }
                }
            }
        }, "t1").start();

        new Thread(() ->{
            for (char i = 1+64; i < (char)27+64; i++) {
                synchronized (o){
                    System.out.println(i);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        o.notifyAll();
                    }
                }
            }
        }, "t2").start();

        //不要将字符串常量作为锁的对象,如下虽然指针是两个，但是实质上锁的是同一个对象，著名的jetty中的bug
        /*String lock1 = "LOCKString";
        String lock2 = "LOCKString";

        new Thread(() ->{
            System.out.println("thread1 start...");
            synchronized (lock1){

                try {
                    System.out.println("thread1 running...");
                    Thread.sleep(9000);
                    lock1.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock1.notifyAll();
                }

            }
        }, "t1").start();

        new Thread(() ->{
            System.out.println("thread2 start...");
            synchronized (lock2){

                try {
                    Thread.sleep(1000);
                    System.out.println("thread2 running...");
                    Thread.sleep(9000);
                    lock2.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock2.notifyAll();
                }

            }
        }, "t2").start();*/

    }
}
