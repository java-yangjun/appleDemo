package com.jung.channel.api.test.communication;

/**
 * Description
 * Author yangjun
 * Date 2020/6/19 4:21 下午
 **/
public class CASVolatileMethod {

    enum Status {T1, T2};

    private volatile static  Status statusFlag = Status.T1;


    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                while (statusFlag != Status.T1){
                }
                System.out.println(i);
                statusFlag= Status.T2;
            }
        }, "t1").start();

        new Thread(() ->{
            for (int i = 1; i < 27; i++) {
                while (statusFlag != Status.T2){
                }
                char charStr = (char) (i+64);
                System.out.println(charStr);
                statusFlag= Status.T1;
            }
        }, "t2").start();

    }
}
