package com.jamie.javastudy.designPattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 原型模式
 * @PackageName: com.jamie.study.designPattern
 * @ClassName: Sheep
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 1:02 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sheep implements Cloneable {
    String name;

    String color;

    int age;

    String address;

    Sheep friend;

    public Sheep(String name, String color, int age, String address) {
        this.age = age;
        this.address = address;
        this.name = name;
        this.color = color;
    }

    @Override
    protected Object clone(){
        Sheep sheep = null;
        try {
            sheep = (Sheep)super.clone();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return sheep;
    }
}
