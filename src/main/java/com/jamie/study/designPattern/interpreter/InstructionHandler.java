package com.jamie.study.designPattern.interpreter;

import java.util.Stack;

/**
 * @PackageName: com.jamie.study.designPattern.interpreter
 * @ClassName: InstructionHandler
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 3:19 下午
 */
public class InstructionHandler {
    private AbstractNode node;

    public void Handle(String instruction){
        //用栈来保存语法树
        AbstractNode left = null, right = null;
        AbstractNode direction = null, action = null, distance = null;
        AndNode andNode = null;
        Stack<AbstractNode> stack = new Stack<>();

        //用空格分割指令
        String[] word = instruction.split(" ");

        for(int i = 0; i < word.length; i ++) {
            if (word[i].equalsIgnoreCase("and")){
                left = stack.pop();
                String wordl = word[++i];
                direction = new DirectionNode(wordl);
                String word2 = word[++i];
                action = new ActionNode(word2);
                String word3 =word[++i];
                distance = new DistanceNode(word3);
                right = new SentenceNode(direction, action, distance);
                andNode = new AndNode(left, right);
                stack.push(andNode);
            } else {
                String word1 = word[i];
                direction = new DirectionNode(word1);
                String word2 = word[++i];
                action = new ActionNode(word2);
                String word3 = word[++i];
                distance = new DistanceNode(word3);
                left= new SentenceNode(direction, action, distance);
                stack.push(left);

            }
        }
        this.node = stack.pop();
    }

    public String output() {
        String result = node.interpret();
        return result;
    }
}
