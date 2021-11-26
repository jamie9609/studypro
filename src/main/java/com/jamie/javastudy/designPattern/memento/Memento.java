package com.jamie.javastudy.designPattern.memento;

import lombok.Data;

/**
 * @PackageName: com.jamie.study.designPattern.memento
 * @ClassName: Memento
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 11:36 上午
 */
@Data
public class Memento {
    private  String  state;

    public Memento(String state) {
        this.state = state;
    }
}
