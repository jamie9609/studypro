package com.jamie.javastudy.designPattern.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.jamie.study.designPattern.decorator
 * @ClassName: Drink
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 10:54 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Drink {

    public String desc;
    private float price = 0.0f;

    /**
     * 计算价格，子类来实现
     * @return
     */
    public abstract float cast();
}
