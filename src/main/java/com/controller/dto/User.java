package com.controller.dto;

import lombok.Data;

/**
 * @PackageName: com.controller.dto
 * @ClassName: User
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/29 2:38 下午
 */
@Data
public class User {
    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别
     */
    private Boolean sex;

    /**
     * 年龄
     */
    private Integer age;
}
