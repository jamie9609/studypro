package com.jamie.javastudy.designPattern.bridge;

/**
 * @PackageName: com.jamie.study.designPattern.bridge
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 6:54 下午
 */
public class Client {

    public static void main(String[] args) {
        // 获取折叠式样式 + 品牌手机
        Phone phone1 = new FoldedPhone(new Xiaomi());

        phone1.call();
        phone1.open();
        phone1.close();

    }
}
