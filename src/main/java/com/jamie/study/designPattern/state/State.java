package com.jamie.study.designPattern.state;

/**
 * @PackageName: com.jamie.study.designPattern.state
 * @InterfaceName: State
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 4:00 下午
 */
public interface State {
    /**
     * 预订房间
     */
    void bookRoom();

    /**
     * 退订房间
     */
    void unsubscribeRoom();

    /**
     * 入住
     */
    void checkInRoom();

    /**
     * 退房
     */
    void checkOutRoom();
}
