package com.jung.channel.api.test.communication;

import java.util.concurrent.locks.LockSupport;

/**
 * Description LockSupportMethod
 * Author yangjun
 * Date 2020/6/19 4:21 下午
 **/
public class LockSupportMethod {

    private static Thread t1 = null;
    private static Thread t2 = null;
    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                System.out.println(i);
                LockSupport.unpark(t2);
                if(i < 26){
                    LockSupport.park();
                }
            }
        }, "t1");

        t2 = new Thread(() ->{
            for (char i = 1+64; i < (char)27+64; i++) {
                System.out.println(i);
                LockSupport.unpark(t1);
                if(i < 26+64){
                    LockSupport.park();
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
