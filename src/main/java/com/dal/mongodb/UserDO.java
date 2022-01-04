package com.dal.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.List;

/**
 * @PackageName: com.dal.mongodb
 * @ClassName: Article
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/4 3:11 下午
 */
@Data
@Document(collection = "user")
public class UserDO {
    @Id   //【主键是自动生成的】
    @GeneratedValue
    private String id;
    private String username;
    private Integer age;
    private String password;
    private String address;
}
