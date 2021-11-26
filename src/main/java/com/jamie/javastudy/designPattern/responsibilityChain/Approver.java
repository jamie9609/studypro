package com.jamie.javastudy.designPattern.responsibilityChain;

/**
 * @PackageName: com.jamie.study.designPattern.responsibilityChain
 * @InterfaceName: Approver
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 5:56 下午
 */
public abstract class Approver {
    /**
     * 下一个处理者
     */
    Approver approver;
    String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    /**
     * 子类去处理请求，所以方法要做为抽象的
     * @param purchaseRequest
     */
    public abstract void processRequest(PurchaseRequest purchaseRequest);
}
