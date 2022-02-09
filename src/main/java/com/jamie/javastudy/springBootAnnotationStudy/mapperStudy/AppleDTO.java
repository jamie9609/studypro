package com.jamie.javastudy.springBootAnnotationStudy.mapperStudy;

import lombok.Data;

/**
 * @PackageName: com.jamie.javastudy.springBootAnnotationStudy.mapper
 * @ClassName: AppleDTO
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/9 5:10 下午
 */
@Data
public class AppleDTO {
    private Integer appleId;

    private String appleName;

    private String appleColor;

    private Integer appleHeavy;

    @Override
    public String toString() {
        return appleId.toString() + "_" + appleName + "_" + appleColor +  "_" + appleHeavy.toString();
    }
}
