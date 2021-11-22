package com.jamie.study.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:35 下午
 */
public class Client {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();

        objectStructure.attach(new Man());
        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());
        Success success = new Success();
        objectStructure.display(success);

        objectStructure.display(new Fail());

        objectStructure.display(new Wait());

    }
}
