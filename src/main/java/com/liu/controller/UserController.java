package com.liu.controller;

import com.liu.VO.RegisterVO;
import com.liu.entity.User;
import com.liu.service.UserService;
import com.liu.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Api("用户相关操作")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/info")
    @ApiOperation("获取用户信息")
    public R loginForUsername(HttpServletRequest request){
        User userInfo = userService.getUserInfo(request);
        return R.success(userInfo).message("获取用户信息成功");
    }

    @ApiOperation(("用户注册"))
    @PostMapping("/register")
    public R register(@RequestBody RegisterVO userVO){
        Map<String, Object> result = userService.register(userVO);
        if(!ObjectUtils.isEmpty(result.get("error"))){
            return R.fail(null).message((String) result.get("error"));
        }
        return R.success(null).message((String) result.get("success"));
    }
}
