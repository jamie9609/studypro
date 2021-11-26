package com.jamie.javastudy.designPattern.observer.improve;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @PackageName: com.jamie.study.designPattern.observer.improve
 * @ClassName: WeatherData
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 10:01 上午
 */
@Data
@AllArgsConstructor
public class WeatherData implements Subject{
    public String temperature;

    public String pressure;

    public String humidity;

    private ArrayList<Observer> observers;

    public WeatherData() {
        observers = new ArrayList<Observer>();
    }

    public void setData(String temperature, String pressure, String humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for(int i = 0 ; i < observers.size(); i ++) {
            observers.get(i).update(temperature, pressure, humidity);
        }
    }
}
