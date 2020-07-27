package com.jung.channel.api.test.design.proxy.staticProxy;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/1 5:53 下午
 **/
public class StaticProxyTest {

    public static void main(String[] args) {

        System.out.println("我是刘德华粉丝，我要签名");
        StarProxy starProxyL = new StarProxy(new LiuDeHuaStar());
        starProxyL.autoGraph();

        System.out.println("我是张学友粉丝，我要签名");
        StarProxy starProxyZ = new StarProxy(new ZhangXueYouStar());
        starProxyZ.autoGraph();
    }
}
