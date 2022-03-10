package com.jamie.javastudy.designPattern.composite2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.composite2
 * @ClassName: Department
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 3:08 下午
 */
public class Department extends OrganizationComponent{
    public Department(String name, String desc) {
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
    protected void add(OrganizationComponent org) {
    }

    @Override
    protected void remove(OrganizationComponent org) {
    }

    @Override
    public void print() {
        System.out.println(getName());
    }
}
