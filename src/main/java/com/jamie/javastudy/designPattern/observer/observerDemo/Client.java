package com.jamie.javastudy.designPattern.observer.observerDemo;

/**
 * @PackageName: com.jamie.javastudy.designPattern.observer.observerDemo
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/14 2:06 下午
 */
public class Client {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        Watcher watcher = new Watcher();
        weatherData.registerObserver(watcher);
        weatherData.setData("10","12","122");
    }
}
