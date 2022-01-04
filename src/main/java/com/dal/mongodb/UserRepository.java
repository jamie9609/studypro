package com.dal.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PackageName: com.dal.mongodb
 * @ClassName: UserRepository
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/4 3:52 下午
 */

@Repository
public interface UserRepository extends MongoRepository<UserDO, String> {
    //这两个方法 是跟据匹配规则自定义出来的方法
    // 此处无法正常写入，需要把@SpringBootApplication注解中的exclude = DataSourceAutoConfiguration.class去掉，
    // 然后在application.properties配置文件中加上spring.datasourc的相关配置就可以了。否则注解扫描repository包会失败，此处先本地实现该接口来规避这个问题
    List<UserDO> findByUsername(String username);

    List<UserDO> findByUsernameLike(String username);
}
