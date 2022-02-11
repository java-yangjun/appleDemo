package com.jung.channel.api.test.design.Observer;

public class AntObserver implements ObserverAnimal {


    @Override
    public void moodHappy() {
        System.out.println("蚂蚁开心。。。");
    }

    @Override
    public void moodSad() {
        System.out.println("蚂蚁不开心。。。");
    }
}
