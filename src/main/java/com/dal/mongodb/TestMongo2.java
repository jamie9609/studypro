package com.dal.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PackageName: com.dal.mongodb
 * @ClassName: TestMongo2
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/4 3:52 下午
 */
@RestController
@RequestMapping("/mongo2")
@Service
public class TestMongo2 {

    @Resource
    private UserRepositoryImpl userRepository;

    //添加
    public UserDO createUser2(String id) {
        UserDO user = new UserDO();
        user.setId(id);
        user.setUsername("张三");
        user.setPassword("admin");
        user.setAge(22);
        user.setAddress("测试地址111");

        UserDO user1 = userRepository.insert(user);
        System.out.println(user1);
        return user1;
    }

    //查询所有
    @GetMapping("findAll")
    public List<UserDO> findAll() {
        List<UserDO> userList = userRepository.findAll();
        return userList;
    }

    //根据id查询
    @GetMapping("findId")
    public UserDO getById() {
        return userRepository.findById("614196627e7800008b0037a5").get();
    }

    //条件查询
    @GetMapping("findQuery")
    public List<UserDO> findUserList() {
        UserDO user = new UserDO();
        user.setUsername("张三");
        user.setAge(22);
        Example<UserDO> userExample = Example.of(user);
        return userRepository.findAll(userExample);
    }

    //模糊查询
    @GetMapping("findLike")
    public void findUsersLikeName() {
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        UserDO user = new UserDO();
        user.setUsername("三");
        Example<UserDO> userExample = Example.of(user, matcher);
        List<UserDO> userList = userRepository.findAll(userExample);
        System.out.println(userList);
    }

    //分页查询
    @GetMapping("findPage")
    public void findUsersPage() {
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        //0为第一页
        Pageable pageable = PageRequest.of(0, 10, sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        UserDO user = new UserDO();
        user.setUsername("三");
        Example<UserDO> userExample = Example.of(user, matcher);
//创建实例
        Example<UserDO> example = Example.of(user, matcher);
        Page<UserDO> pages = userRepository.findAll(example, pageable);
        System.out.println(pages);
    }

    //修改
    @GetMapping("update")
    public void updateUser() {
        //先根据id查询问文档信息
        UserDO user = userRepository.findById("60b8d57ed539ed5b124942de").get();
        user.setUsername("张三");
        user.setAge(25);
        user.setPassword("abxxxxxx");
        //如果当前文档存在 自动执行更新
        UserDO save = userRepository.save(user);
        System.out.println(save);
    }

    //删除
    @GetMapping("delete")
    public void delete() {
        userRepository.deleteById("60b8d57ed539ed5b124942de");
    }

    //接口中自定义查询方法
    @GetMapping("findByUsername")
    public List<UserDO> findByUsername(String username) {
        //return userRepository.findByUsername(username);
        return null;
    }
}