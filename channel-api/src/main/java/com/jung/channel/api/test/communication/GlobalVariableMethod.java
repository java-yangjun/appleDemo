package com.jung.channel.api.test.communication;

/**
 * Description 全局变量控制
 * Author yangjun
 * Date 2020/6/19 4:21 下午
 **/
public class GlobalVariableMethod {

    private static boolean flag = true;
    private static int switchEnd = 0;

    private static int num = 1;
    private static int charNum = 1;


    private static synchronized int getSwitchEnd(){
        return switchEnd;
    }
    private static synchronized void increaseSwitch(){
        switchEnd++;
    }

    public static void main(String[] args) {

        new Thread(() -> {
            while (getSwitchEnd()<2){
                if(flag){
                    if(num == 27){
                        increaseSwitch();
                    } else {
                        System.out.println(num);
                        num++;
                        flag = false;
                    }
                }
            }
        }, "t1").start();

        new Thread(() ->{
            while (getSwitchEnd()<2){
                if(!flag){
                    if(charNum == 27){
                        increaseSwitch();
                    } else {
                        char charStr = (char) (charNum+64);
                        System.out.println(charStr);
                        charNum++;
                        flag = true;
                    }
                }
            }
        }, "t2").start();

    }
}
