package com.jamie.javastudy.designPattern.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: ObjectStructure
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:32 下午
 */
public class ObjectStructure {
    private List<Person> persons = new LinkedList<>();

    public void attach(Person p) {
        persons.add(p);
    }

    public void detach(Person p) {
        persons.remove(p);
    }

    public void display(Action action) {
        for (Person p : persons) {
            p.accept(action);
        }
    }
}
