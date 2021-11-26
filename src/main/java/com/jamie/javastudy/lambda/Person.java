package com.jamie.javastudy.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.jamie.study.lambda
 * @ClassName: Person
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/15 4:24 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    Integer age;
    String name;
}
