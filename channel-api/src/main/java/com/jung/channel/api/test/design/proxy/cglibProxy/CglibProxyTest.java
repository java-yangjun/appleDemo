package com.jung.channel.api.test.design.proxy.cglibProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/1 5:53 下午
 **/
public class CglibProxyTest {

    public static void main(String[] args) throws Exception {

        LiuDeHuaStar liuDeHuaStar = new LiuDeHuaStar();
        ZhangXueYouStar zhangXueYouStar = new ZhangXueYouStar();

        BenChiCar benChiCar = new BenChiCar();
        BaoMaCar baoMaCar = new BaoMaCar();


        System.out.println("我是刘德华粉丝，要签名");
        CglibProxy proxy1 = new CglibProxy(liuDeHuaStar);
        LiuDeHuaStar o1 = (LiuDeHuaStar)proxy1.getProxy();
        o1.autoGraph();



        System.out.println("我是张学友粉丝，要签名");
        CglibProxy proxy2 = new CglibProxy(zhangXueYouStar);
        ZhangXueYouStar o2 = (ZhangXueYouStar)proxy2.getProxy();
        o2.autoGraph();

        System.out.println("管家去开我奔驰");
        CglibProxy proxy3 = new CglibProxy(benChiCar);
        BenChiCar o3 = (BenChiCar)proxy3.getProxy();
        o3.carName();
        System.out.println(o3.price);


        System.out.println("管家去开我宝马");
        CglibProxy proxy4 = new CglibProxy(baoMaCar);
        BaoMaCar o4 = (BaoMaCar)proxy4.getProxy();
        o4.carName();
        System.out.println(o4.price);
    }
}
