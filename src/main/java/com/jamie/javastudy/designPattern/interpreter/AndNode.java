package com.jamie.javastudy.designPattern.interpreter;

/**
 * @PackageName: com.jamie.study.designPattern.interpreter
 * @ClassName: AndNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 3:10 下午
 */
public class AndNode extends AbstractNode{
    /**
     * 左表达式
     */
    private AbstractNode left;
    /**
     * 右表达式
     */
    private AbstractNode right;

    public AndNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String interpret() {
        return left.interpret() + "再" + right.interpret();
    }
}
