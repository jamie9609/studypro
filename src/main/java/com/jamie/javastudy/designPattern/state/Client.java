package com.jamie.javastudy.designPattern.state;

/**
 * @PackageName: com.jamie.study.designPattern.state
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 4:10 下午
 */
public class Client {
    public static void main(String[] args) {
        //有2间房
        Room[] rooms = new Room[2];
        //初始化
        for(int i = 0 ; i < rooms.length ; i++){
            rooms[i] = new Room();
        }

        //第一间房
        //预定
        rooms[0].bookRoom();
        //取消
        rooms[0].unsubscribeRoom();
        //预订
        rooms[0].bookRoom();
        //入住
        rooms[0].checkInRoom();
        //预订
        rooms[0].bookRoom();
        System.out.println(rooms[0].getState());
        System.out.println("---------------------------");

        //第二间房
        rooms[1].checkInRoom();
        rooms[1].bookRoom();
        rooms[1].checkOutRoom();
        rooms[1].bookRoom();
        System.out.println(rooms[1].toString());
    }
}
