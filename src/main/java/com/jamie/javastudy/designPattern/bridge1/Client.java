package com.jamie.javastudy.designPattern.bridge1;

/**
 * @PackageName: com.jamie.javastudy.designPattern.bridge1
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:03 下午
 */
public class Client {

    public static void main(String[] args) {
        WxMsgSend wxMsgSend = new WxMsgSend();
        HuaWeiPhoneSend huaWeiPhoneSend = new HuaWeiPhoneSend(wxMsgSend);
        huaWeiPhoneSend.sendMsg();
        XiaoMiPhoneSend xiaoMiPhoneSend = new XiaoMiPhoneSend(wxMsgSend);
        xiaoMiPhoneSend.sendMsg();
    }
}
