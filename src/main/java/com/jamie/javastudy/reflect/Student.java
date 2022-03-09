package com.jamie.javastudy.reflect;

import lombok.Data;

/**
 * @PackageName: com.jamie.javastudy.reflect
 * @ClassName: Student
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/24 8:44 下午
 */
@Data
public class Student {

    public String name;
    public Integer age;
    private Integer area;

    public Student(){
    }

    public Student(String name, Integer age, Integer area){
        this.age = age;
        this.area = area;
        this.name = name;
    }

    public void PrintClass() {
        System.out.println("二年级了");
    }
}
