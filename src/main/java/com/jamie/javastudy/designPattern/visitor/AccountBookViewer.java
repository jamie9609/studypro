package com.jamie.javastudy.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: AccountBookViewer
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:57 下午
 */
public interface AccountBookViewer {
    /**
     * 查看消费账单
     * @param bill
     */
    void view(ConsumeBill bill);

    /**
     * 查看收入账单
     * @param bill
     */
    void view(IncomeBill bill);
}
