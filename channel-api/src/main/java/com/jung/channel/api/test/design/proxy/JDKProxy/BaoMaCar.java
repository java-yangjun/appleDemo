package com.jung.channel.api.test.design.proxy.JDKProxy;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/1 6:20 下午
 **/
public class BaoMaCar implements Car{

    public Integer price = 30;

    @Override
    public void carName() {
        System.out.println("宝马");
    }
}
