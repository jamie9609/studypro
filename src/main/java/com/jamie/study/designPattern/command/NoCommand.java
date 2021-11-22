package com.jamie.study.designPattern.command;

/**
 * 没有任何命令，即空执行。用于初始化每个按钮，当调用空命令时，对象什么都不用做。其实这也是一种设计模式。
 * @PackageName: com.jamie.study.designPattern.command
 * @ClassName: NoCommand
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 3:58 下午
 */
public class NoCommand implements Command{
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
