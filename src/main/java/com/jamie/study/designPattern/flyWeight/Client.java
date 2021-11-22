package com.jamie.study.designPattern.flyWeight;

/**
 * @PackageName: com.jamie.study.designPattern.flyWeight
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 11:36 上午
 */
public class Client {

    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        WebSite webSite1 = webSiteFactory.getWebSiteCategory("新闻");
        webSite1.use(new User("zhangsan"));


    }
}
