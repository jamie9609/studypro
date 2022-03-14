package com.jamie.javastudy.designPattern.responsibilityChain2;

import com.jamie.javastudy.designPattern.responsibilityChain.PurchaseRequest;

/**
 * @PackageName: com.jamie.javastudy.designPattern.responsibilityChain2
 * @ClassName: SchoolMasterApprover
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/11 10:32 上午
 */
public class SchoolMasterApprover extends Approver{
    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() > 30000 && purchaseRequest.getPrice() < 100000) {
            System.out.println(" 请求编号 ID=" + purchaseRequest.getId() + "被" + this.name + "处理" );
        } else {
            if (approver == null) {
                System.out.println("暂不处理");
            }else {
                approver.processRequest(purchaseRequest);
            }
        }
    }
}
