package com.jamie.javastudy.designPattern.composite2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.composite2
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 3:15 下午
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
        computerCollege.print();
        university.print();
    }
}
