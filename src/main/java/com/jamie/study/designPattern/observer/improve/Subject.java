package com.jamie.study.designPattern.observer.improve;

/**
 * @PackageName: com.jamie.study.designPattern.observer.improve
 * @InterfaceName: Subject
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 9:56 上午
 */
public interface Subject {
    /**
     * 注册观察者
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 删除观察者
     * @param o
     */
    void removeObserver(Observer o);

    /**
     * 通知所有观察者
     */
    void notifyObserver();

}
