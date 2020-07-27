package com.jung.channel.api.test.volatileTest;

import lombok.SneakyThrows;

/**
 * Description volatile修饰变量可见性测试demo
 * Author yangjun
 * Date 2020/6/16 10:19 上午
 **/
public class VisibilityDemo extends Thread{
    //设置类静态变量,各线程访问这同一共享变量
    private  static /*volatile*/ boolean flag = false;
    //无限循环,等待flag变为true时才跳出循环
    //@SneakyThrows
    public void run() {
        System.out.println(Thread.currentThread().getId() + "开始运行！");
        while (!flag){
            /*if(Thread.currentThread().getId() == 12){
                //System.out.println(Thread.currentThread().getId());
                Thread.sleep(100);
            }*/
        };
        System.out.println(Thread.currentThread().getId() + "停止了");
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 50000; i++) {
            new VisibilityDemo().start();
            new VisibilityDemo().start();
        }
        //new VisibilityDemo().start();
        System.out.println("当前flag是"+flag);
        //sleep的目的是等待线程启动完毕,也就是说进入run的无限循环体了
        Thread.sleep(100);
        flag = true;
        System.out.println("当前flag是"+flag);
    }
}