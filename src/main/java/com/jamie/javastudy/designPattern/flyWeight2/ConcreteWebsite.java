package com.jamie.javastudy.designPattern.flyWeight2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.flyWeight2
 * @ClassName: ConcreateWebsite
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 10:25 上午
 */
public class ConcreteWebsite extends Website{
    private String name = "";

    public ConcreteWebsite(String name) {
        this.name = name;
    }

    @Override
    public void use() {
        System.out.println("网站分类：" + name);
    }
}
