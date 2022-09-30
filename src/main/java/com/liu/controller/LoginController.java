package com.liu.controller;

import com.liu.VO.LoginVO;
import com.liu.mapper.UserMapper;
import com.liu.service.impl.LoginServiceImpl;
import com.liu.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api("登录模块")
public class LoginController {

    @Value("${token.header}")
    private String header;

    @Autowired
    private LoginServiceImpl loginService;


    @PostMapping("/login")
    @ApiOperation("登录获取token")
    public R login(@RequestBody LoginVO loginVO){
        String token = loginService.login(loginVO);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put(header,token);
        return R.success(tokenMap).message("登录成功");
    }

}
