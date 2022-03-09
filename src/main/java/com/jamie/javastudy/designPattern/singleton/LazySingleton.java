package com.jamie.javastudy.designPattern.singleton;

/**
 * @PackageName: com.jamie.javastudy.designPattern.singleton
 * @ClassName: SingletonTest
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 10:43 上午
 */
public class LazySingleton {
    private static volatile LazySingleton instance = null;

    private LazySingleton() {
        System.out.println("单例实现");
    }

    public static synchronized LazySingleton getInstance(){
        if (instance == null) {
            return new LazySingleton();
        }
        return instance;

    }
}
