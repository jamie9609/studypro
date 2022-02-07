package com.jamie.javastudy.createObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @PackageName: com.jamie.javastudy.createObject
 * @ClassName: CreateObjectMethod
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/7 3:57 下午
 */
public class CreateObjectMethod {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, CloneNotSupportedException {
        // 第一种方法：new
        Apple apple = new Apple();
        apple.setColor("red");
        apple.setHeavy(11);
        apple.setName("富士");

        // 第二种方法：使用class的newInstance 反射原理
        Apple apple2 = (Apple)Class.forName("com.jamie.javastudy.createObject.Apple").newInstance();
        apple2.setColor("red");
        apple2.setHeavy(12);
        apple2.setName("阿克苏");

        // 第三种方法：使用constructor的newInstance 反射原理。第二种方法也是调用的这个
        Constructor<Apple> constructor = Apple.class.getConstructor();
        Apple apple3 = constructor.newInstance();
        apple3.setColor("red");
        apple3.setHeavy(13);
        apple3.setName("阿克苏");

        // 第四种方法，clone。前提：需要实现Cloneable接口
        Apple apple4 = (Apple)apple.clone();
        apple4.setColor("green");
        apple4.setHeavy(14);
        apple4.setName("烟台");


        // 第五种方法：反序列化。略 https://blog.csdn.net/w410589502/article/details/56489294

        System.out.println(apple2.toString());
        System.out.println(apple3.toString());
        System.out.println(apple4.toString());
        System.out.println(apple.toString());
    }

}
