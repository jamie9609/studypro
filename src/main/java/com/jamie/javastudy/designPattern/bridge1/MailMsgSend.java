package com.jamie.javastudy.designPattern.bridge1;

/**
 * @PackageName: com.jamie.javastudy.designPattern.bridge1
 * @ClassName: MailMsgSend
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:02 下午
 */
public class MailMsgSend implements PhoneImplementor{
    @Override
    public void send() {
        System.out.println("发邮件");
    }
}
