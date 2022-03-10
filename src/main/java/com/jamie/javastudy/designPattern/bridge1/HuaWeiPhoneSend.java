package com.jamie.javastudy.designPattern.bridge1;

/**
 * @PackageName: com.jamie.javastudy.designPattern.bridge1
 * @ClassName: HuaWeiPhoneSend
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:00 下午
 */
public class HuaWeiPhoneSend extends PhoneSendAbstract{
    public HuaWeiPhoneSend(PhoneImplementor phoneImplementor) {
        super(phoneImplementor);
    }

    @Override
    public void sendMsg() {
        super.sendMsg();
        System.out.println("华为手机发");
    }
}
