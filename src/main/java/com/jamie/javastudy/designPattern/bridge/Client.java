package com.jamie.javastudy.designPattern.bridge;

/**
 * @PackageName: com.jamie.study.designPattern.bridge
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 6:54 下午
 */
public class Client {

    public static void main2(String[] args) {
        // 获取折叠式样式 + 品牌手机
        Phone phone1 = new FoldedPhone(new Xiaomi());

        phone1.call();
        phone1.open();
        phone1.close();

    }

    public static void main(String[] args) {
        //创建具体的实现对象
        MessageImplementor impl = new MessageSMS();
        //创建普通消息对象
        AbstractMessage message = new CommonMessage(impl);
        message.sendMessage("加班申请速批","李总");

        //将实现方式切换成邮件，再次发送
        impl = new MessageEmail();
        //创建加急消息对象
        message = new UrgencyMessage(impl);
        message.sendMessage("加班申请速批","李总");
    }
}
