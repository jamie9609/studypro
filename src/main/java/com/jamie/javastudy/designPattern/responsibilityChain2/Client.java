package com.jamie.javastudy.designPattern.responsibilityChain2;

import com.jamie.javastudy.designPattern.responsibilityChain.PurchaseRequest;

/**
 * @PackageName: com.jamie.javastudy.designPattern.responsibilityChain2
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/11 10:40 上午
 */
public class Client {
    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 100000, 1);
        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("王校长");
        CollegeApprover collegeApprover = new CollegeApprover("李院长");
        DepartmentApprover departmentApprover = new DepartmentApprover("张主任");
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(schoolMasterApprover);

        collegeApprover.processRequest(purchaseRequest);
    }
}
