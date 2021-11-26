package com.jamie.javastudy.designPattern.deepClone;

import java.io.Serializable;

/**
 * @PackageName: com.jamie.study.designPattern
 * @ClassName: DeepCloneabletarget
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 1:53 下午
 */
public class DeepCloneableTarget implements Serializable, Cloneable {
    public static final long serialVersionUID = 1L;

    private String cloneName;
    private String cloneClass;

    public DeepCloneableTarget(String cloneName, String cloneClass){
        this.cloneClass = cloneClass;
        this.cloneName = cloneName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
