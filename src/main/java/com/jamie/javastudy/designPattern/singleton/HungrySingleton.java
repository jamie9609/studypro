package com.jamie.javastudy.designPattern.singleton;

/**
 * @PackageName: com.jamie.javastudy.designPattern.singleton
 * @ClassName: HungrySingleton
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 10:53 上午
 */
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){
    };

    public static HungrySingleton getInstance() {
        return instance;
    }
}
