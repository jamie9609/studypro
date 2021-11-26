package com.jamie.javastudy.designPattern.command;

/**
 * @PackageName: com.jamie.study.designPattern.command
 * @ClassName: LightOffCommand
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 3:56 下午
 */
public class LightOffCommand implements Command{
    //聚合lighterReiver
    LightReceiver light;

    public LightOffCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
