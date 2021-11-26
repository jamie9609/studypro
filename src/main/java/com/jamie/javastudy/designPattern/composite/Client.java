package com.jamie.javastudy.designPattern.composite;

/**
 * @PackageName: com.jamie.study.designPattern.composite
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 9:37 上午
 */
public class Client {

    public static void main(String[] args) {
        //从大到校创建

        University university = new University("buaa", "好学校");
        College computerCollege = new College("计算机学院", "好的学院");
        College chineseCollege = new College("文学院", "很好的学院");
        university.add(computerCollege);
        university.add(chineseCollege);

        computerCollege.add(new Department("软件工程", "好的专业"));
        computerCollege.add(new Department("信息工程", "好的信息专业"));
        computerCollege.add(new Department("通信工程", "好的通信专业"));

        chineseCollege.add(new Department("中文系", "好的中文专业"));
        chineseCollege.add(new Department("英文系", "好的英文专业"));

        //university.print();

        chineseCollege.print();
    }
}
