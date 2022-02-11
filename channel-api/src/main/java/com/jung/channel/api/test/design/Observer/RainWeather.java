package com.jung.channel.api.test.design.Observer;

public class RainWeather extends Weather {

    @Override
    void notifyWeather() {
        observers.forEach(observerAnimal -> {
            if(observerAnimal instanceof AntObserver){
                observerAnimal.moodSad();
            } else {
                observerAnimal.moodHappy();
            }
        });
    }
}
