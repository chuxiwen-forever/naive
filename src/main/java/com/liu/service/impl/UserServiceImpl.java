package com.liu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liu.DTO.SysUserDTO;
import com.liu.VO.RegisterVO;
import com.liu.VO.SysUserVO;
import com.liu.entity.User;
import com.liu.exception.GlobalException;
import com.liu.mapper.UserMapper;
import com.liu.security.LoginUser;
import com.liu.service.UserService;
import com.liu.utils.ResultCodeEnum;
import com.liu.utils.TokenService;
import com.liu.utils.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public User getUserInfo(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        return loginUser.getUser();
    }

    @Override
    public Map<String, Object> register(RegisterVO userVO) {
        Map<String,Object> result = new HashMap<>();
        LambdaQueryWrapper<User> eq = new LambdaQueryWrapper<>();
        eq.eq(User::getUsername,userVO.getUsername());
        User user = userMapper.selectOne(eq);
        if(!ObjectUtils.isEmpty(user)){
            result.put("error","该用户已经存在");
            return result;
        }
        user = new User();
        user.setSalt(UUIDUtils.getUUID());
        userVO.setPassword(passwordEncoder.encode(secret + userVO.getPassword() + user.getSalt()));
        BeanUtils.copyProperties(userVO,user);
        userMapper.insert(user);
        result.put("success","注册成功");
        return result;
    }

    @Override
    public List<SysUserDTO> getAllUsers() {
        List<User> userList = userMapper.selectList(null);
        return userList.stream().map(user -> {
            SysUserDTO userDTO = new SysUserDTO();
            BeanUtils.copyProperties(user,userDTO);
            userDTO.setOnlineStatus(null);
            return userDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void updateUser(SysUserVO userVO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,userVO.getId());
        User targetUser = userMapper.selectOne(queryWrapper);
        queryWrapper.clear();
        queryWrapper.eq(User::getUsername,userVO.getUsername());
        User ifExitUser = userMapper.selectOne(queryWrapper);
        System.out.println("=====");
        if(!ObjectUtils.isEmpty(ifExitUser)){
            throw new GlobalException(ResultCodeEnum.FAIL.getCode(), "该用户名已经存在");
        }
        User user = new User();
        if(!(userVO.getPassword() == null || "".equals(userVO.getPassword()))){
            userVO.setPassword(passwordEncoder.encode(secret + userVO.getPassword() + targetUser.getSalt()));
        }
        BeanUtils.copyProperties(userVO,user);
        userMapper.updateById(user);
    }

    @Override
    public void addUser(SysUserVO userVO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,userVO.getUsername());
        User ifExitUser = userMapper.selectOne(queryWrapper);
        if(!ObjectUtils.isEmpty(ifExitUser)){
            throw new GlobalException(ResultCodeEnum.FAIL.getCode(), "该用户名已经存在");
        }
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        user.setSalt(UUIDUtils.getUUID());
        user.setPassword(passwordEncoder.encode(secret + user.getPassword() + user.getSalt()));
        userMapper.insert(user);
    }
}
