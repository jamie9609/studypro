package com.jamie.study.designPattern.interpreter;

/**
 * @PackageName: com.jamie.study.designPattern.interpreter
 * @ClassName: SentenceNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 3:14 下午
 */
public class SentenceNode extends AbstractNode{

    private AbstractNode direction;
    private AbstractNode action;
    private AbstractNode distance;

    public SentenceNode(AbstractNode direc,AbstractNode a,AbstractNode d) {
        this.direction = direc;
        this.action = a;
        this.distance = d;
    }
    @Override
    public String interpret() {
        return direction.interpret() + action.interpret() + distance.interpret();
    }
}
