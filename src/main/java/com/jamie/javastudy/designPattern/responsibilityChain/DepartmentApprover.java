package com.jamie.javastudy.designPattern.responsibilityChain;

/**
 * @PackageName: com.jamie.study.designPattern.responsibilityChain
 * @ClassName: DepartmentApprover
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 6:01 下午
 */
public class DepartmentApprover extends Approver{

    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() <= 5000) {
            System.out.println(" 请求编号 ID=" + purchaseRequest.getId() + "被" + this.name + "处理" );
        } else {
            approver.processRequest(purchaseRequest);
        }
    }
}