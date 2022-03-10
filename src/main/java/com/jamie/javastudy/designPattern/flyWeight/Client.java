package com.jamie.javastudy.designPattern.flyWeight;

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


        Integer i1 = new Integer(50);
        Integer i2 = new Integer(50);
        System.out.println(i1 == i2);

        Integer i3 = new Integer(500);
        Integer i4 = new Integer(500);
        System.out.println(i3 == i4);

        //需要注意下面这种方式存在隐式装箱
        Integer i5 = 50;
        Integer i6 = 50;
        System.out.println(i5 == i6);

        Integer i7 = 500;
        Integer i8 = 500;
        System.out.println(i7 == i8);
    }
}
