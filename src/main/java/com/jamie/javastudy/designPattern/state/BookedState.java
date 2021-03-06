package com.jamie.javastudy.designPattern.state;

/**
 * @PackageName: com.jamie.study.designPattern.state
 * @InterfaceName: BookedState
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 4:04 下午
 */
public class BookedState implements State{
    Room hotelManagement;

    public BookedState(Room hotelManagement) {
        this.hotelManagement = hotelManagement;
    }

    @Override
    public void bookRoom() {
        System.out.println("该房间已近给预定了...");
    }

    @Override
    public void checkInRoom() {
        System.out.println("入住成功...");
        hotelManagement.setState(hotelManagement.getCheckInState());
    }

    @Override
    public void checkOutRoom() {
        //不需要做操作
    }
    @Override
    public void unsubscribeRoom() {
        System.out.println("退订成功,欢迎下次光临...");
        hotelManagement.setState(hotelManagement.getFreeTimeState());
    }
}
