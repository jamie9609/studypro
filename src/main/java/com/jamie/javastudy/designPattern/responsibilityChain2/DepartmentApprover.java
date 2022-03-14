package com.jamie.javastudy.designPattern.responsibilityChain2;

import com.jamie.javastudy.designPattern.responsibilityChain.PurchaseRequest;

/**
 * @PackageName: com.jamie.javastudy.designPattern.responsibilityChain2
 * @ClassName: DepartmentApprover
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 8:54 下午
 */
public class DepartmentApprover extends Approver{
    public DepartmentApprover(String name) {
        super(name);
    }
    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() <= 5550) {
            System.out.println(" 请求编号 ID=" + purchaseRequest.getId() + "被" + this.name + "处理" );
        } else {
            approver.processRequest(purchaseRequest);
        }
    }
}
