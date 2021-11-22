package com.jamie.study.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: InsumeBill
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 6:02 下午
 */
public class IncomeBill implements Bill{
    private double amount;

    private String item;

    public IncomeBill(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    @Override
    public void accept(AccountBookViewer viewer) {
        viewer.view(this);
    }

    public double getAmount() {
        return amount;
    }

    public String getItem() {
        return item;
    }
}
