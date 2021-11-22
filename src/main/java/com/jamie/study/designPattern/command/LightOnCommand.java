package com.jamie.study.designPattern.command;

/**
 * @PackageName: com.jamie.study.designPattern.command
 * @ClassName: LightOnCommand
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 3:52 下午
 */
public class LightOnCommand implements Command{
    //聚合lighterReiver
    LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
