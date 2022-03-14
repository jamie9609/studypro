package com.jamie.javastudy.designPattern.responsibilityChain2;

import com.jamie.javastudy.designPattern.responsibilityChain.PurchaseRequest;

/**
 * @PackageName: com.jamie.javastudy.designPattern.responsibilityChain2
 * @ClassName: CollegeApprover
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/11 10:30 上午
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
