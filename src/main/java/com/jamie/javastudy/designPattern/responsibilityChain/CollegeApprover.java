package com.jamie.javastudy.designPattern.responsibilityChain;

/**
 * @PackageName: com.jamie.study.designPattern.responsibilityChain
 * @ClassName: CollegeApprover
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 6:07 下午
 */
public class CollegeApprover extends Approver{

    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() > 5000 && purchaseRequest.getPrice() <= 10000) {
            System.out.println(" 请求编号 ID=" + purchaseRequest.getId() + "被" + this.name + "处理" );
        } else {
            approver.processRequest(purchaseRequest);
        }
    }
}
