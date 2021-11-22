package com.jamie.study.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @InterfaceName: Bill
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:57 下午
 */
public interface Bill {
    void accept(AccountBookViewer viewer);
}
