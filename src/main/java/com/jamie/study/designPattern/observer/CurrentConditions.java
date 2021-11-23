package com.jamie.study.designPattern.observer;

/**
 * @PackageName: com.jamie.study.designPattern.observer
 * @ClassName: CurrentConditions
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 9:34 上午
 */
public class CurrentConditions {
    private String temperature;
    private String pressure;
    private String humidity;

    public void update(String temperature, String pressure, String humidity) {
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
