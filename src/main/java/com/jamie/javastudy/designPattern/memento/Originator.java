package com.jamie.javastudy.designPattern.memento;

import lombok.Data;

/**
 * @PackageName: com.jamie.study.designPattern.memento
 * @ClassName: Originator
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 11:34 上午
 */
@Data
public class Originator {

    private String state;

    public Memento saveStateMemento(){
        return new Memento(state);
    }

    public void getStateMemento(Memento memento){
        state = memento.getState();
    }
}
