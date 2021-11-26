package com.jamie.javastudy.designPattern.responsibilityChain;

/**
 * @PackageName: com.jamie.study.designPattern.responsibilityChain
 * @ClassName: SchoolMasterApprover
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 6:09 下午
 */
public class SchoolMasterApprover extends Approver{
    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() > 30000) {
            System.out.println(" 请求编号 ID=" + purchaseRequest.getId() + "被" + this.name + "处理" );
        } else {
            approver.processRequest(purchaseRequest);
        }
    }
}
