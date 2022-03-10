package com.jamie.javastudy.designPattern.decorator2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.decorator2
 * @InterfaceName: Coffee
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:33 下午
 */
public interface Coffee {
    /** 获取价格 */
    double getCost();
    /** 获取配料 */
    String getMaterial();
}
