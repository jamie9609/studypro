package com.jamie.javastudy.springBootAnnotationStudy.mapperStudy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.jamie.javastudy.springBootAnnotationStudy.mapper
 * @ClassName: AppleDO
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/9 5:11 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppleDO {
    private String name;

    private String color;

    private Integer heavy;

    private Integer id;
}
