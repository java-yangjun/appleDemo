package com.jung.channel.api.test.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/28 2:15 下午
 **/
public class ExecutorsTest {

    public void test(){

        //无核心线程数，总线程数无限制（Integer.MAX_VALUE）闲置60s销毁
        ExecutorService ex1 = Executors.newCachedThreadPool();
        //有限线程池，核心线程数=总数，闲置不会回收，linkedBlockingQueue
        ExecutorService ex2 = Executors.newFixedThreadPool(10);
        //单线程，linkedBlockingQueue
        ExecutorService ex3 = Executors.newSingleThreadExecutor();
        //指定核心线程数
        ExecutorService ex4 = Executors.newScheduledThreadPool(10);


    }

}
