package com.liu.controller;

import com.liu.DTO.SysUserDTO;
import com.liu.VO.SysUserVO;
import com.liu.VO.other.DeleteToPostVO;
import com.liu.service.UserService;
import com.liu.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("后台管理操作用户信息")
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/infos")
    @ApiOperation("获取所有的用户信息")
    public R getAllUserMessage(){
        List<SysUserDTO> userDTOS = userService.getAllUsers();
        return R.success(userDTOS).message("获取成功");
    }

    @PostMapping("/delete")
    @ApiOperation("删除用户信息(逻辑删除)")
    public R deleteUser(@RequestBody DeleteToPostVO vo){
        userService.deleteUser(vo.getId());
        return R.success(null).message("删除成功");
    }

    @PostMapping("/update")
    @ApiOperation("更新用户信息")
    public R updateUser(@RequestBody SysUserVO userVO){
        userService.updateUser(userVO);
        return R.success(null).message("修改成功");
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public R addUser(@RequestBody SysUserVO userVO){
        userService.addUser(userVO);
        return R.success(null).message("添加成功");
    }

}
