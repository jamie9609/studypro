package com.jamie.study.designPattern.state;

/**
 * @PackageName: com.jamie.study.designPattern.state
 * @ClassName: FreeTimeState
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 4:08 下午
 */
public class FreeTimeState implements State{
    Room hotelManagement;

    public FreeTimeState(Room hotelManagement){
        this.hotelManagement = hotelManagement;
    }

    @Override
    public void bookRoom() {
        System.out.println("您已经成功预订了...");
        hotelManagement.setState(hotelManagement.getBookedState());
    }

    @Override
    public void checkInRoom() {
        System.out.println("您已经成功入住了...");
        hotelManagement.setState(hotelManagement.getCheckInState());
    }

    @Override
    public void checkOutRoom() {
        //不需要做操作
    }

    @Override
    public void unsubscribeRoom() {
        //不需要做操作
    }
}
