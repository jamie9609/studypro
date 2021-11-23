package com.jamie.study.designPattern.interpreter;

/**
 * @PackageName: com.jamie.study.designPattern.interpreter
 * @ClassName: DistanceNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 3:18 下午
 */
public class DistanceNode extends AbstractNode{

    private String distance;

    public DistanceNode(String d) {
        this.distance=d;
    }
    @Override
    public String interpret() {
        return distance;
    }
}
