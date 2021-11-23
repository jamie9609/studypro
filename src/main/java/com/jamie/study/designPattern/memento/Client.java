package com.jamie.study.designPattern.memento;

/**
 * @PackageName: com.jamie.study.designPattern.memento
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 11:40 上午
 */
public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("状态1");
        caretaker.add(originator.saveStateMemento());


        originator.setState("状态2");
        caretaker.add(originator.saveStateMemento());

        originator.setState("状态3");
        caretaker.add(originator.saveStateMemento());

        System.out.println("当前的状态：" + originator.getState());
        //状态恢复到状态1
        originator.getStateMemento(caretaker.get(0));
        System.out.println("当前的状态：" + originator.getState());
    }
}
