package com.jamie.javastudy.designPattern.bridge1;

/**
 * @PackageName: com.jamie.javastudy.designPattern.bridge1
 * @ClassName: PhoneSendAbstract
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 7:55 下午
 */
public abstract class PhoneSendAbstract {
    public PhoneImplementor phoneImplementor;

    public PhoneSendAbstract(PhoneImplementor phoneImplementor) {
        this.phoneImplementor = phoneImplementor;
    }

    public void sendMsg() {
        phoneImplementor.send();
    }
}
