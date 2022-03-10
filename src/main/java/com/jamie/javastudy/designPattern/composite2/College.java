package com.jamie.javastudy.designPattern.composite2;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.jamie.javastudy.designPattern.composite2
 * @ClassName: College
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 3:11 下午
 */
public class College extends University{
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String desc) {
        super(name, desc);
    }

    @Override
    public String getDesc() {
        return super.getDesc();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void print() {
        System.out.println("---------------" + getName() +"-----------------");
        for(OrganizationComponent item :organizationComponents) {
            item.print();
        }
    }

}
