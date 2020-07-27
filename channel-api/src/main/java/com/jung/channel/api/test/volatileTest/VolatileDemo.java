package com.jung.channel.api.test.volatileTest;

import java.util.concurrent.CountDownLatch;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/15 6:22 下午
 **/
public class VolatileDemo extends Thread{
    //volatile保证inc的单机多核全局可见
    public volatile int inc = 0;

    //synchronized保证自增操作原子性
    public /*synchronized*/ void increase() throws InterruptedException {
        //System.out.println(inc);
        Thread.sleep(1);
        inc++; //++操作分为：1、读取inc，2、inc+1， 3、新值赋给inc
    }

    public static void main(String[] args) {
        process2();
    }

    private static void process2() {
        final int count = 50;
        final CountDownLatch latch = new CountDownLatch(count);
        final VolatileDemo test = new VolatileDemo();
        for(int i=0; i<count; i++){
            new Thread(()->{
                for(int j=0;j<10000;j++){
                    try {
                        test.increase();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                latch.countDown();  //count值减1
            }).start();
        }
        try {
            latch.await();  //等待count = 0时唤醒，主线程再往下执行，保证前面的线程都执行完
        } catch (Exception e) {
        }
        System.out.println("result:--------------------" + test.inc);
    }
}
