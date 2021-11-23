package com.jamie.study.designPattern.interpreter;

/**
 * @PackageName: com.jamie.study.designPattern.interpreter
 * @ClassName: AbstractNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 3:09 下午
 */
public abstract class AbstractNode {
    /**
     * 解释器：运算
     * @return
     */
    public abstract String interpret();
}
