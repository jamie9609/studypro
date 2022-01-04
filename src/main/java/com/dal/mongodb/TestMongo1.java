package com.dal.mongodb;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @PackageName: com.dal.mongodb
 * @ClassName: TestMongo1
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/4 3:20 下午
 */
@RestController
@RequestMapping("/mongo1")
public class TestMongo1 {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void createUser(String id){
        UserDO user = new UserDO();
        user.setId(id);
        user.setAge(20);
        user.setUsername("test");
        user.setSchool("university");
        user.setAddress("address1");
        user.setPassword("password1");
        UserDO user1 = mongoTemplate.insert(user);

        //返回来 有自己生成的 id
        System.out.println(user1);
    }


    //查询所有
    @GetMapping("findAll")
    public void findUser() {
        List<UserDO> userList = mongoTemplate.findAll(UserDO.class);
        System.out.println("userList = " + userList);
    }

    //根据id查询
    @GetMapping("findById")
    public UserDO getById(){
        UserDO user = mongoTemplate.findById("614196627e7800008b0037a5", UserDO.class);
        return user;
    }

    //条件查询
    @GetMapping("findUser")
    public List<UserDO> findUserList() {

        //筛选条件
        Query query = new Query(Criteria
                .where("username").is("张三")
                .and("age").is(23)
        );

        //执行查询
        List<UserDO> userList = mongoTemplate.find(query, UserDO.class);
        return userList;

    }

    //模糊查询
    @GetMapping("findLike")
    public List<UserDO> findUsersLikeName() {
        //正则表达式 模糊查询【】
        String username = "张";
        String regex = String.format("%s%s%s", "^.*", username, ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        //名字符合正则表达式的条件
        Query query = new Query(Criteria.where("username").regex(pattern));
        //mongodb 文档相对应的模型类
        List<UserDO> userList = mongoTemplate.find(query, UserDO.class);
        return userList;
    }

    //分页查询
    @GetMapping("findPage")
    public void findUsersPage() {
        String name = "est";
        int pageNo = 1;
        int pageSize = 10;

        //构造条件
        Query query = new Query();
        //正则表达式匹配
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        //添加条件
        query.addCriteria(Criteria.where("name").regex(pattern));
        //开始统计记录数
        int totalCount = (int) mongoTemplate.count(query, UserDO.class);
        //分页查询 返回结果
        List<UserDO> userList = mongoTemplate.find(query.skip((pageNo - 1) * pageSize).limit(pageSize), UserDO.class);

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", userList);
        pageMap.put("totalCount",totalCount);
        System.out.println(pageMap);
    }
    //修改
    @GetMapping("update")
    public void updateUser() {
        //修改一般是查询出来整个文档【document mongodb里面的叫法】
        UserDO user = mongoTemplate.findById("5ffbfa2ac290f356edf9b5aa", UserDO.class);
        user.setUsername("test_1");
        user.setAge(25);
        user.setPassword("714468438@qq.com");

        //修改好相应的数据 根据id进行修改 id是查询出来的
        Query query = new Query(Criteria.where("_id").is(user.getId()));
        Update update = new Update();
        update.set("username", user.getUsername());
        update.set("age", user.getAge());
        update.set("password", user.getPassword());
        //执行更新
        UpdateResult result = mongoTemplate.upsert(query, update, UserDO.class);
        long count = result.getModifiedCount();
        System.out.println(count);
    }

    //删除操作
    @GetMapping("delete")
    public void delete() {

        //构造条件 根据id删除相应的文档
        Query query = new Query(Criteria.where("_id").is("5ffbfa2ac290f356edf9b5aa"));
        DeleteResult result = mongoTemplate.remove(query, UserDO.class);
        long count = result.getDeletedCount();
        System.out.println(count);
    }
}
