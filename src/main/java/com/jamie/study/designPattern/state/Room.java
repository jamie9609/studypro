package com.jamie.study.designPattern.state;

import lombok.Data;

/**
 * @PackageName: com.jamie.study.designPattern.state
 * @ClassName: Room
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 4:02 下午
 */
@Data
public class Room {
    /**
     * 房间的三个状态
     * 空闲状态;
     * 入住状态;
     * 预订状态;
     */
    State freeTimeState;
    State checkInState;
    State bookedState;

    State state ;

    public Room(){
        freeTimeState = new FreeTimeState(this);
        checkInState = new CheckInState(this);
        bookedState = new BookedState(this);
        //初始状态为空闲
        state = freeTimeState ;
    }

    /**
     * @desc 预订房间
     * @return void
     */
    public void bookRoom(){
        state.bookRoom();
    }

    /**
     * @desc 退订房间
     * @return void
     */
    public void unsubscribeRoom(){
        state.unsubscribeRoom();
    }

    /**
     * @desc 入住
     * @return void
     */
    public void checkInRoom(){
        state.checkInRoom();
    }

    /**
     * @desc 退房
     * @return void
     */
    public void checkOutRoom(){
        state.checkOutRoom();
    }

    @Override
    public String toString(){
        return "该房间的状态是:"+getState().getClass().getName();
    }
}
