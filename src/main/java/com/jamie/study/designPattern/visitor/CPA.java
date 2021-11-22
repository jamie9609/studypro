package com.jamie.study.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: CPA
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 6:06 下午
 */
public class CPA implements AccountBookViewer{
    @Override
    public void view(ConsumeBill bill) {
        if ("工资".equals(bill.getItem())) {
            System.out.println("注会查看工资是否交个人所得税。");
        }
    }

    @Override
    public void view(IncomeBill bill) {
        System.out.println("注会查看收入交税了没。");
    }
}
