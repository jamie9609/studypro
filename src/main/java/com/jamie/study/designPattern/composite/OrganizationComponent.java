package com.jamie.study.designPattern.composite;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.jamie.study.designPattern.composite
 * @ClassName: organizationComponent
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 9:19 上午
 */
@Data
@NoArgsConstructor
public abstract class OrganizationComponent {
    private String name;
    private String desc;

    public OrganizationComponent(String name, String desc) {
        this.desc = desc;
        this.name = name;
    }

    protected void add (OrganizationComponent organizationComponent){
        // 默认实现
        throw new UnsupportedOperationException();
    }

    protected void remove (OrganizationComponent organizationComponent){
        // 默认实现
        throw new UnsupportedOperationException();
    }

    /**
     * 打印
     */
    protected abstract void print();

}
