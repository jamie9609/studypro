package com.jamie.javastudy.designPattern.interpreter;

/**
 * @PackageName: com.jamie.study.designPattern.interpreter
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 3:35 下午
 */
public class Client {
    public static void main(String[] args) {
        String instruction ="down run 10 and left move 20";
        InstructionHandler iHandler = new InstructionHandler();
        iHandler.Handle(instruction);
        String outString ;
        outString =iHandler.output();
        System.out.println(outString);
    }
}
