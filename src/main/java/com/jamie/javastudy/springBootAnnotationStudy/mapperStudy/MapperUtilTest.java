package com.jamie.javastudy.springBootAnnotationStudy.mapperStudy;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

/**
 * @PackageName: com.jamie.javastudy.springBootAnnotationStudy.mapper
 * @ClassName: MapperUtilTest
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/9 5:09 下午
 */
@Mapper(componentModel = "spring")
public interface MapperUtilTest {
    @Mappings({
            @Mapping(source = "appleDO.id", target = "appleId"),
            @Mapping(source = "appleDO.name", target = "appleName"),
            @Mapping(source = "appleDO.color", target = "appleColor"),
            @Mapping(source = "appleDO.heavy", target = "appleHeavy", qualifiedByName = "convertHeavy")
    })
    AppleDTO convert(AppleDO appleDO);

    @Named("convertHeavy")
    default Integer convert (Integer heavy) {
        return heavy > 10? -100000: -999999;
    }
}
