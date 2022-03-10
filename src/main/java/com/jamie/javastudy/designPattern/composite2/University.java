package com.jamie.javastudy.designPattern.composite2;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.jamie.javastudy.designPattern.composite2
 * @ClassName: University
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 3:01 下午
 */
public class University extends OrganizationComponent{

    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent org) {
        organizationComponents.add(org);
    }

    @Override
    protected void remove(OrganizationComponent org) {
        organizationComponents.remove(org);
    }

    @Override
    public void print() {
        System.out.println("---------------" + getName() +"-----------------");
        for(OrganizationComponent item : organizationComponents) {
            item.print();
        }
    }
}
