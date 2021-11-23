package com.jamie.study.designPattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.jamie.study.designPattern.memento
 * @ClassName: Caretaker
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 11:38 上午
 */
public class Caretaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento memento) {
        mementoList.add(memento);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}
