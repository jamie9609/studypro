package com.controller;

import com.controller.dto.User;
import com.dal.mongodb.TestMongo1;
import com.dal.mongodb.TestMongo2;
import com.dal.mongodb.UserDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.Random;

/**
 * 接口文档地址：http://localhost:8082/swagger-ui.html
 * @PackageName: com.controller
 * @ClassName: swaggerDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/29 2:24 下午
 */

@Api(value = "用户模块", tags = "用户基础信息接口模块")
@RestController
@RequestMapping("/user")
@ResponseBody
public class swaggerDemo {

    @Resource
    private TestMongo1 testMongo1;

    @Resource
    private TestMongo2 testMongo2;

    /**
     * 一个路径参数的接口示例
     */
    @ApiOperation(value = "查询用户信息", notes = "根据用户id查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "getUserById/{id}")
    public User getUserById(@PathVariable(name = "id") String id) {
        User user = new User();
        user.setId(String.valueOf(new Random().nextInt(1000)));
        user.setName("user" + id);
        user.setSex(true);
        user.setAge(new Random().nextInt(30));
        return user;
    }

    @ApiOperation(value = "写入mongodb数据测试", notes = "mongodb测试")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "create/{id}")
    public UserDO createMongoUser(@PathVariable(name = "id") String id) {
        UserDO user = new UserDO();
        user.setId(id);
        testMongo1.createUser(id);
        //testMongo2.createUser2(id);
        return user;
    }
}
