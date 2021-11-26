package com.jamie.javastudy.designPattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: AccountBook
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 6:08 下午
 */
public class AccountBook {
    //单子列表
    private List<Bill> billList = new ArrayList<>();

    //添加单子
    public void addBill(Bill bill){
        billList.add(bill);
    }
    //供账本的查看者查看账本
    public void show(AccountBookViewer viewer){
        for (Bill bill : billList) {
            bill.accept(viewer);
        }
    }

}
