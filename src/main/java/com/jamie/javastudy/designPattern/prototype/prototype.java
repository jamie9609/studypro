package com.jamie.javastudy.designPattern.prototype;

/**
 * @PackageName: com.jamie.study.designPattern
 * @ClassName: prototype
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 1:04 下午
 */
public class prototype {

    public static void main(String[] args) {
        Sheep sheep = new Sheep("zhangsan", "red", 10, "china");
        sheep.setFriend(new Sheep("lisi", "white", 12, "usa"));

        Sheep sheep1 = (Sheep)sheep.clone();
        Sheep sheep2 = (Sheep)sheep.clone();
        System.out.println("sheep=" + sheep.toString() + "friend=" + sheep.friend.hashCode());
        System.out.println("sheep1=" + sheep1.toString() + "friend=" + sheep1.friend.hashCode());
        System.out.println("sheep2=" + sheep2.toString() + "friend=" + sheep2.friend.hashCode());

        System.out.println(sheep1 == sheep2);

        System.out.println(sheep1.getAddress() == sheep2.getAddress());

        System.out.println(sheep1.getAddress().equals(sheep2.getAddress()));
    }

}
