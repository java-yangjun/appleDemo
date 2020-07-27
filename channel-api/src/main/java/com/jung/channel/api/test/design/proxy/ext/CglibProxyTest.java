package com.jung.channel.api.test.design.proxy.ext;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/8 5:33 下午
 **/
public class CglibProxyTest {

    public static void main(String[] args) {
        ShareBicycle bicycle = new ShareBicycle();

        CglibProxy cglibProxy = new CglibProxy(bicycle);

        ShareBicycle son = (ShareBicycle)cglibProxy.getProxy();

        son.sayName();
    }
}
