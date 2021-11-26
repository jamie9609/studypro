package com.jamie.javastudy.designPattern.state;

/**
 * @PackageName: com.jamie.study.designPattern.state
 * @ClassName: CheckInState
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 4:07 下午
 */
public class CheckInState implements State{

    Room hotelManagement;

    public CheckInState(Room hotelManagement) {
        this.hotelManagement = hotelManagement;
    }

    @Override
    public void bookRoom() {
        System.out.println("该房间已经入住了...");
    }
    @Override
    public void checkInRoom() {
        System.out.println("该房间已经入住了...");
    }
    @Override
    public void checkOutRoom() {
        System.out.println("退房成功....");
        hotelManagement.setState(hotelManagement.getFreeTimeState());
    }
    @Override
    public void unsubscribeRoom() {
        //不需要做操作
    }
}
