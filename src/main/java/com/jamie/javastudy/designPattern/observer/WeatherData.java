package com.jamie.javastudy.designPattern.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.jamie.study.designPattern.observer
 * @ClassName: WeatherData
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 9:39 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    public String temperature;

    public String pressure;

    public String humidity;

    public CurrentConditions currentConditions;

    public WeatherData(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }

    public void dataChange() {
        currentConditions.update(temperature, pressure, humidity);
    }

    public void setData(String temperature, String pressure, String humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        dataChange();
    }
}
