package com.jung.channel.api.test.design.proxy.ext;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/2 4:48 下午
 **/
public class Apple implements Fruits {

    @Override
    public void sayName() {
        System.out.println("我就是苹果！");
    }

    @Override
    public void howMany(Integer number) {
        for (int i = 0; i < number; i++) {
            System.out.println("我是一个苹果！");
        }
    }
}
