package com.jamie.javastudy.designPattern.bridge;

/**
 * @PackageName: com.jamie.javastudy.designPattern.bridge
 * @ClassName: CommonMessage
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 7:42 下午
 */
public class CommonMessage extends AbstractMessage{
    /**
     * 构造方法，传入实现部分的对象
     *
     * @param impl 实现部分的对象
     */
    public CommonMessage(MessageImplementor impl) {
        super(impl);
    }

    @Override
    public void sendMessage(String message, String toUser) {
        // 对于普通消息，直接调用父类方法，发送消息即可
        super.sendMessage(message, toUser);
    }
}
