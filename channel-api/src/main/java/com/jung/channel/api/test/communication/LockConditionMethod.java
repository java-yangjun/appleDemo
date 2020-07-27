package com.jung.channel.api.test.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/22 4:41 下午
 **/
public class LockConditionMethod {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {

                try {
                    lock.lock();
                    System.out.println(i);
                    c2.signal();
                    c1.await();
                    c2.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                try {
                    lock.lock();
                    System.out.println((char) (i + 64));
                    c1.signal();
                    c2.await();
                    c1.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
