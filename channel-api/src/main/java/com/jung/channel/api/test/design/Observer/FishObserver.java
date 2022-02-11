package com.jung.channel.api.test.design.Observer;

public class FishObserver implements ObserverAnimal {
    @Override
    public void moodHappy() {
        System.out.println("小鱼开心。。。");
    }

    @Override
    public void moodSad() {
        System.out.println("小鱼不开心。。。");
    }
}
