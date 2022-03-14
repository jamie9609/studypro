package com.jamie.javastudy.designPattern.observer.observerDemo;

/**
 * @PackageName: com.jamie.javastudy.designPattern.observer.damo
 * @ClassName: Watcher
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/14 11:47 上午
 */
public class Watcher implements Observer2{
    private String temperature;
    private String pressure;
    private String humidity;

    @Override
    public void updateWeather(String temperature, String pressure, String humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display(){
        System.out.println("今天天气的温度是：" + temperature);
        System.out.println("今天天气的气压是：" + pressure);
        System.out.println("今天天气的湿度是：" + humidity);
    }
}
