package com.jamie.javastudy.designPattern.bridge;

/**
 * 实现发消息的统一接口
 * @PackageName: com.jamie.javastudy.designPattern.bridge
 * @InterfaceName: MessageImplementor
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 7:36 下午
 */
public interface MessageImplementor {
    /**
     * 发送消息
     * @param message 要发送消息的内容
     * @param toUser  消息的接受者
     */
    void send(String message, String toUser);
}
