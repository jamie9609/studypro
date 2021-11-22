package com.jamie.study.designPattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.jamie.study.designPattern.composite
 * @ClassName: College
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 9:33 上午
 */
public class College extends University{

    /**
     * 存放的是college
     */
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String desc) {
        super(name, desc);
    }

    // 重写add方法


    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
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
    protected void print() {
        System.out.println("---------------" + getName() +"-----------------");

        for(OrganizationComponent item :organizationComponents) {
            item.print();
        }
    }
}
