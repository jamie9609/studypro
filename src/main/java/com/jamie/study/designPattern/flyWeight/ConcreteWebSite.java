package com.jamie.study.designPattern.flyWeight;

/**
 * @PackageName: com.jamie.study.designPattern.flyWeight
 * @ClassName: ConcreteWebSite
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 11:22 上午
 */
public class ConcreteWebSite extends WebSite{

    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为：" + type + "在使用中，使用者是：" + user.getName());
    }
}
