package com.jung.channel.api.test.communication;

import java.util.concurrent.CountDownLatch;

/**
 * Description
 * Author yangjun
 * Date 2020/6/19 4:21 下午
 **/
public class AtomicIntegerMethod2 {

    static volatile boolean at = true;
    private static  CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {

            for (int i = 1; i < 27; i++) {
                while (!at){
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                at = false;
            }
            System.out.println("t1停止了！");
            countDownLatch.countDown();
        }, "t1").start();

        new Thread(() ->{
            for (int i = 1; i < 27; i++) {
                while (at){
                }
                System.out.println(i);
                at = true;
            }
            System.out.println("t2停止了！");
            countDownLatch.countDown();
        }, "t2").start();


        Thread.sleep(300);
        at = false;
        countDownLatch.await();
        System.out.println("主程结束！");

    }
}
