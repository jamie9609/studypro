package com.jamie.javastudy.designPattern.bridge;

/**
 * @PackageName: com.jamie.javastudy.designPattern.bridge
 * @ClassName: MessageEmail
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 7:41 下午
 */
public class MessageEmail implements MessageImplementor {

    @Override
    public void send(String message, String toUser) {
        System.out.println("使用邮件短消息的方法，发送消息'"+message+"'给"+toUser);
    }

}
