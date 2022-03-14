package com.jamie.javastudy.designPattern.observer.observerDemo;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

/**
 * @PackageName: com.jamie.javastudy.designPattern.observer.observerDemo
 * @ClassName: WeatherData
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/14 11:59 上午
 */
@AllArgsConstructor
public class WeatherData implements Subject{

    public String temperature;

    public String pressure;

    public String humidity;

    private ArrayList<Observer2> observers;

    public WeatherData() {
        observers = new ArrayList<Observer2>();
    }

    public void setData(String temperature, String pressure, String humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer2 observer2) {
        observers.add(observer2);
    }

    @Override
    public void notifyObserver() {
        observers.stream().forEach(v -> v.updateWeather(temperature, pressure, humidity));
    }
}
