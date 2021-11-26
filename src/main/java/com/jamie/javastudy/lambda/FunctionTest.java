package com.jamie.javastudy.lambda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @PackageName: com.jamie.study.lambda
 * @ClassName: Function
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/15 4:02 下午
 */
public class FunctionTest {

    public static void main(String[] args) {

        System.out.println(LocalDateTime.parse("2021-11-15T00:00:00"));

        // Function 接受一个参数，返回一个结果
        Function<Integer,Integer> lambdaFunc = o -> o * o;

        Function<Integer, String> function2 = lambdaFunc.andThen(o -> "result is " + o);

        String apply1 = function2.apply(2);
        System.out.println(apply1);


        // BiFunction 接受两个参数，返回一个结果

        BiFunction<Integer, Integer, Integer>  lambdaFunc2 = (x, y) -> (x + y);

        Integer apply2 = lambdaFunc2.apply(10, 2);
        System.out.println(apply2);



        Person zhangsan = new Person(20,"zhangsan");
        Person lisi = new Person(30,"lisi");
        Person wangwu = new Person(40,"wangwu");

        List<Person> personList = new ArrayList<>();
        personList.add(zhangsan);
        personList.add(lisi);
        personList.add(wangwu);


        List<Person> zhangsan1 = getPersonByName("zhangsan", personList);
        List<Person> personByAge = getPersonByAge(20, personList);
        System.out.println(zhangsan1);
        System.out.println(personByAge);

        List<Person> personByAge2 = getPersonByAge2(20, personList, (age, list) -> list.stream()
                .filter(p -> p.getAge() > age).collect(Collectors.toList()));
        System.out.println(personByAge2);

    }

    public static List<Person> getPersonByName(String name, List<Person> personList){
        return personList.stream().filter(v -> v.getName().equals(name)).collect(Collectors.toList());
    }

    public static List<Person> getPersonByAge(int age, List<Person> personList){
        BiFunction<Integer, List<Person>, List<Person>> biFunction
                = (ageOfPerson, persons) -> personList.stream().filter(person -> person.getAge() > 20).collect(Collectors.toList());
        return biFunction.apply(age, personList);
    }

    // 更加灵活的方式 让调用者实现过滤的条件 是大于还是小于
    public static List<Person> getPersonByAge2(int age, List<Person> personList, BiFunction<Integer, List<Person>, List<Person>> biFunction){
        return biFunction.apply(age,personList);
    }
}
