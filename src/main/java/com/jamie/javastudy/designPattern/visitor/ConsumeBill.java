package com.jamie.javastudy.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: ConsumeBill
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:59 下午
 */
public class ConsumeBill implements Bill {

    private double amount;

    private String item;

    public ConsumeBill(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    /**
     * 观察者模式的关键
     * @param viewer
     */
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