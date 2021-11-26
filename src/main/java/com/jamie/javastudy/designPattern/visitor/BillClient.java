package com.jamie.javastudy.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: BillClient
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 6:10 下午
 */
public class BillClient {
    public static void main(String[] args) {

        AccountBook accountBook = new AccountBook();
        accountBook.addBill(new IncomeBill(10000, "卖广告"));
        accountBook.addBill(new IncomeBill(4000, "卖商品"));

        accountBook.addBill(new ConsumeBill(200, "工资"));
        accountBook.addBill(new ConsumeBill(2200, "材料费"));

        Boss boss = new Boss();
        CPA cpa = new CPA();
        accountBook.show(cpa);
        accountBook.show(boss);
        boss.getTotalConsume();
        boss.getTotalIncome();
        //cpa.view(new IncomeBill(10000, "卖广告"));
    }
}

