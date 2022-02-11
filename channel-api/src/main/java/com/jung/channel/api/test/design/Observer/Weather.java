package com.jung.channel.api.test.design.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Weather {

    public List<ObserverAnimal> observers = new ArrayList<>();

    public void add(ObserverAnimal observerAnimal){
        observers.add(observerAnimal);
    }

    public void remover(ObserverAnimal observerAnimal){
        observers.remove(observerAnimal);
    }

    abstract void notifyWeather();
}
