package com.jung.channel.api.test.communication;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * Description
 * Author yangjun
 * Date 2020/6/19 4:21 下午
 **/
public class BlockingQueueMethod {

    static BlockingQueue q1 = new ArrayBlockingQueue<String>(1);
    static BlockingQueue q2 = new ArrayBlockingQueue<String>(1);
    ExecutorService mFixedThreadPool= Executors.newFixedThreadPool(1);
    ExecutorService mScheduledThreadPool = Executors.newScheduledThreadPool(2);
    ExecutorService a = Executors.newSingleThreadExecutor();
    ExecutorService c = Executors.newCachedThreadPool();
    ExecutorService b = new ThreadPoolExecutor(1,1,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());


    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                try {
                    q2.put("ok");
                    q1.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                char charStr = (char) (i+64);
                System.out.println(charStr);
            }
        }, "t1").start();

        new Thread(() ->{
            for (int i = 1; i < 27; i++) {
                try {
                    q1.put("ok");
                    q2.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }, "t2").start();

    }
}
