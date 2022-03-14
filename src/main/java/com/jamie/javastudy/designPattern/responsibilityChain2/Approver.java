package com.jamie.javastudy.designPattern.responsibilityChain2;

import com.jamie.javastudy.designPattern.responsibilityChain.PurchaseRequest;

/**
 * @PackageName: com.jamie.javastudy.designPattern.responsibilityChain2
 * @ClassName: Approver
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 8:50 下午
 */
public abstract class Approver {

    public Approver approver;
    public String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver){
        this.approver = approver;
    }

    public abstract void processRequest(PurchaseRequest purchaseRequest);
}
