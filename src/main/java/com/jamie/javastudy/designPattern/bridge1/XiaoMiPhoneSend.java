package com.jamie.javastudy.designPattern.bridge1;

/**
 * @PackageName: com.jamie.javastudy.designPattern.bridge1
 * @ClassName: XiaoMiPhoneSend
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 7:57 下午
 */
public class XiaoMiPhoneSend extends PhoneSendAbstract{

    public XiaoMiPhoneSend(PhoneImplementor phoneImplementor) {
        super(phoneImplementor);
    }
    @Override
    public void sendMsg() {
        super.sendMsg();
        System.out.println("小米手机发");
    }
}
