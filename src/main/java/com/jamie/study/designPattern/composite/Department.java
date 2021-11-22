package com.jamie.study.designPattern.composite;

/**
 * @PackageName: com.jamie.study.designPattern.composite
 * @ClassName: Department
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 9:35 上午
 */
public class Department extends OrganizationComponent {

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
    protected void print() {
        System.out.println(getName());
    }
}
