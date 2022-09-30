package com.liu.service;

import com.liu.DTO.SysUserDTO;
import com.liu.VO.RegisterVO;
import com.liu.VO.SysUserVO;
import com.liu.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 根据请求的token返回用户信息
     * @param request 携带的token
     * @return User
     */
    User getUserInfo(HttpServletRequest request);

    /**
     * 根据Vo层进行注册
     * @param userVO 前端表单数据
     * @return 结果集
     */
    Map<String,Object> register(RegisterVO userVO);

    /**
     * 查询所有user信息
     * @return user列表
     */
    List<SysUserDTO> getAllUsers();

    /**
     * 根据用户id删除用户信息
     * @param id 用户id
     */
    void deleteUser(Long id);

    /**
     * 根据前端信息进行信息修改
     * @param userVO 用户信息
     */
    void updateUser(SysUserVO userVO);

    /**
     * 添加用户
     * @param userVO 前端用户信息
     */
    void addUser(SysUserVO userVO);
}
