package com.jamie.javastudy.designPattern.flyWeight2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.flyWeight2
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 10:43 上午
 */
public class Client {

    public static void main(String[] args) {
        WebsiteFactory factory = new WebsiteFactory();

        factory.getWebsite("产品展示");
        Website fx = factory.getWebsite("产品展示");
        fx.use();
        Website fy = factory.getWebsite("产品展示");
        fy.use();
        Website fz = factory.getWebsite("产品展示");
        fz.use();
        Website fa = factory.getWebsite("博客");
        fa.use();
        Website fb = factory.getWebsite("博客");
        fb.use();
        Website fc = factory.getWebsite("博客");
        fc.use();
        System.out.println("网站分类总数为：" + factory.getWebsiteCount());
    }

}
