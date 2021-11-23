package com.jamie.study.designPattern.observer;

/**
 * @PackageName: com.jamie.study.designPattern.observer
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 9:44 上午
 */
public class Client {
    public static void main(String[] args) {
        CurrentConditions currentConditions = new CurrentConditions();
        WeatherData weatherData = new WeatherData(currentConditions);
        weatherData.setData("30","150","80");

    }
}
