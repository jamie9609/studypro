package com.jamie.javastudy.designPattern.observer.observerDemo;

/**
 * @PackageName: com.jamie.javastudy.designPattern.observer.observerDemo
 * @ClassName: Subject
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/14 11:57 上午
 */
public interface Subject {
    void registerObserver(Observer2 observer2);

    void notifyObserver();
}
