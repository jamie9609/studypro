package com.jamie.javastudy.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @PackageName: com.jamie.javastudy.reflect
 * @ClassName: ReflectTest
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/24 8:44 下午
 */
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = Class.forName("com.jamie.javastudy.reflect.Student");
        System.out.println(aClass);

        Class<Student> aClass1 = Student.class;
        System.out.println(aClass1);

        Student student = new Student();
        Class<? extends Student> aClass2 = student.getClass();
        System.out.println(aClass2);


        Field[] fields = aClass.getFields();
        for (Field  field : fields) {
            System.out.println(field);
        }

        Constructor<?> constructor = aClass.getConstructor(String.class, Integer.class, Integer.class);
        Student s1tudent = (Student) constructor.newInstance("jamie", 11,20);
        Student s1tudent2 = (Student) aClass.newInstance();

        Method[] methods = aClass1.getMethods();

        for (Method  method : methods) {
            System.out.println(method);
        }

        System.out.println(s1tudent);
        System.out.println(s1tudent2);
    }
}
