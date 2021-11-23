package com.jamie.study.designPattern.responsibilityChain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @PackageName: com.jamie.study.designPattern.responsibilityChain
 * @ClassName: PurchaseRequest
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 5:54 下午
 */
@Data
@AllArgsConstructor
public class PurchaseRequest {
    private int type = 0;
    private float price = 0.0f;
    private int id = 0;
}
