package com.jung.channel.api.test.design.Observer;

public class SunnyWeather extends Weather {

    @Override
    void notifyWeather() {
        for (ObserverAnimal observer:observers) {
            if(observer instanceof AntObserver){
                observer.moodHappy();
            } else {
                observer.moodSad();
            }
        }
    }
}
