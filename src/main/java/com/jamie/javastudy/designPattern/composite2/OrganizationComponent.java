package com.jamie.javastudy.designPattern.composite2;

import lombok.Data;

/**
 * @PackageName: com.jamie.javastudy.designPattern.composite2
 * @ClassName: OrganizationComponent
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 2:58 下午
 */

@Data
public abstract class OrganizationComponent {
    private String name;
    private String desc;

    public OrganizationComponent(String name, String desc) {
        this.desc = desc;
        this.name = name;
    }

    protected abstract void add(OrganizationComponent org);

    protected abstract void remove(OrganizationComponent org);

    public abstract void print();
}
