package com.jamie.javastudy.designPattern.command;

/**
 * @PackageName: com.jamie.study.designPattern.command
 * @ClassName: RemoteController
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 4:00 下午
 */
public class RemoteController {

    Command[] onCommands;
    Command[] offCommands;

    Command undoCommand;

    //初始化
    public RemoteController() {
        onCommands = new Command[5];
        offCommands = new Command[5];

        for (int i = 0; i < 5; i ++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    public void setCommands(int no, Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    public void onButtonWasPushed(int no) {
        onCommands[no].execute();
        undoCommand = onCommands[no];
    }

    public void offButtonWasPushed(int no) {
        offCommands[no].execute();
        undoCommand = offCommands[no];
    }

    public void undoWasPushed(int no) {
        undoCommand.undo();
    }

 }
