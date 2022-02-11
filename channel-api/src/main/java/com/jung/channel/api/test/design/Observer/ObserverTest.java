package com.jung.channel.api.test.design.Observer;


public class ObserverTest {

    public static void main(String[] args) {
        ObserverAnimal ant = new AntObserver();
        ObserverAnimal fish = new FishObserver();

        System.out.println("晴天。。。。");
        Weather sunny = new SunnyWeather();
        sunny.add(ant);
        sunny.add(fish);
        sunny.notifyWeather();

        System.out.println("下雨了。。");
        Weather rain = new RainWeather();
        rain.add(ant);
        rain.add(fish);
        rain.notifyWeather();

    }
}
