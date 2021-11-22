package com.jamie.study.designPattern.command;

/**
 * @PackageName: com.jamie.study.designPattern.command
 * @ClassName: RemoteController
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 3:50 下午
 */
public interface Command {
    /**
     * 执行命令
     */
    void execute();

    /**
     * 撤销命令
     */
    void undo();
}
