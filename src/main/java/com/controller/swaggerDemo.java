package com.controller;

import com.controller.dto.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import java.util.Random;

/**
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

}
