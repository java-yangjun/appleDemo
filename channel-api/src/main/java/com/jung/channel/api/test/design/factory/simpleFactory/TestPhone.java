package com.jung.channel.api.test.design.factory.simpleFactory;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 6:58 下午
 **/
public class TestPhone {

    public static void main(String[] args) {
        PhoneFactory factory = new PhoneFactory();
        Phone apple = factory.makePhone("apple");
        Phone mi = factory.makePhone("mi");
        apple.make();
        mi.make();
        System.out.println("apple:" + apple);
        System.out.println("mi:" + mi);
    }
}
