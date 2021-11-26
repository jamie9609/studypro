package com.jamie.javastudy.designPattern.command;

/**
 * @PackageName: com.jamie.study.designPattern.command
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 4:51 下午
 */
public class Client {
    public static void main(String[] args) {
        //接受者
        LightReceiver lightReceiver = new LightReceiver();
        // 创建电灯开关命令
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);

        //再来一个遥控器
        RemoteController remoteController = new RemoteController();

        //设置命令no = 0 是电灯
        remoteController.setCommands(0,lightOnCommand,lightOffCommand);
        System.out.println("-------------按下灯的开按钮------------------");
        remoteController.onButtonWasPushed(0);
        System.out.println("-------------按下电灯的关按钮------------------");
        remoteController.offButtonWasPushed(0);
        System.out.println("-------------按下电灯的撤销按钮------------------");
        remoteController.undoWasPushed(0);


    }
}
