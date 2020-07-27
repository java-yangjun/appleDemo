package com.jung.channel.api.test.design.proxy.JDKProxy;

import jdk.nashorn.internal.scripts.JD;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/1 5:53 下午
 **/
public class JDKProxyTest {

    public static void main(String[] args) throws Exception {

        LiuDeHuaStar liuDeHuaStar = new LiuDeHuaStar();
        ZhangXueYouStar zhangXueYouStar = new ZhangXueYouStar();

        BenChiCar benChiCar = new BenChiCar();
        BaoMaCar baoMaCar = new BaoMaCar();

        System.out.println("我是刘德华粉丝，要签名");
        InvocationHandler invocationHandler1 = new JDKProxy(liuDeHuaStar);
        Class<? extends LiuDeHuaStar> aClass1 = liuDeHuaStar.getClass();
        Star star1 = (Star)Proxy.newProxyInstance(aClass1.getClassLoader(), aClass1.getInterfaces(), invocationHandler1);
        star1.autoGraph();


        System.out.println("我是张学友粉丝，要签名");
        InvocationHandler invocationHandler2 = new JDKProxy(zhangXueYouStar);
        Class<? extends ZhangXueYouStar> aClass2 = zhangXueYouStar.getClass();
        Star star2 = (Star) Proxy.newProxyInstance(aClass2.getClassLoader(), aClass2.getInterfaces(), invocationHandler2);
        star2.autoGraph();

        System.out.println("管家去开我奔驰");
        InvocationHandler invocationHandler3 = new JDKProxy(benChiCar);
        Class<? extends BenChiCar> aClass3 = benChiCar.getClass();
        Car car1 = (Car)Proxy.newProxyInstance(aClass3.getClassLoader(), aClass3.getInterfaces(), invocationHandler3);
        car1.carName();


        System.out.println("管家去开我宝马");
        Class<? extends BaoMaCar> aClass4 = baoMaCar.getClass();
        InvocationHandler invocationHandler4 = new JDKProxy(baoMaCar);
        Car car2 = (Car)Proxy.newProxyInstance(aClass4.getClassLoader(), aClass4.getInterfaces(), invocationHandler4);
        car2.carName();
    }
}
