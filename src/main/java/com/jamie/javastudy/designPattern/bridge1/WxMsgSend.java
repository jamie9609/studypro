package com.jamie.javastudy.designPattern.bridge1;

/**
 * @PackageName: com.jamie.javastudy.designPattern.bridge1
 * @ClassName: WxMsgSend
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:02 下午
 */
public class WxMsgSend implements PhoneImplementor{
    @Override
    public void send() {
        System.out.println("发微信");
    }
}
