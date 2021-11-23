package com.jamie.study.designPattern.observer.improve;

/**
 * @PackageName: com.jamie.study.designPattern.observer.improve
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 10:09 上午
 */
public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditions currentConditions = new CurrentConditions();
        weatherData.registerObserver(currentConditions);
        weatherData.setData("10","12","122");
    }
}
