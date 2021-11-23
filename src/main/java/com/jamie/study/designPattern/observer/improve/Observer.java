package com.jamie.study.designPattern.observer.improve;

/**
 * @PackageName: com.jamie.study.designPattern.observer.improve
 * @InterfaceName: Observer
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 9:59 上午
 */
public interface Observer {

    void update(String temperature, String pressure, String humidity);
}
