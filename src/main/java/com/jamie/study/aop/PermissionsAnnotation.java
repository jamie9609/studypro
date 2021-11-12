package com.jamie.study.aop;

import java.lang.annotation.*;

/**
 * @PackageName: com.jamie.study.aop
 * @InterfaceName: PermissionAnnotation
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/12 10:53 上午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionsAnnotation {
}
