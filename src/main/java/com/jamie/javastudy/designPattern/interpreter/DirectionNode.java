package com.jamie.javastudy.designPattern.interpreter;

/**
 * @PackageName: com.jamie.study.designPattern.interpreter
 * @ClassName: DirectionNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 3:16 下午
 */
public class DirectionNode extends AbstractNode{

    private String direction;

    public DirectionNode(String dir) {
        this.direction = dir;
    }
    @Override
    public String interpret() {
        if(direction.equalsIgnoreCase("up")) {
            return "向上";
        }else if(direction.equalsIgnoreCase("down")) {
            return "向下";
        }else if(direction.equalsIgnoreCase("left")) {
            return "向左";
        }else if(direction.equalsIgnoreCase("right")) {
            return "向右";
        }else {
            return "无效指令";
        }
    }
}
