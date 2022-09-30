package com.liu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liu.VO.LoginVO;
import com.liu.entity.User;
import com.liu.exception.GlobalException;
import com.liu.mapper.UserMapper;
import com.liu.security.LoginUser;
import com.liu.utils.RedisCache;
import com.liu.utils.ResultCodeEnum;
import com.liu.utils.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class LoginServiceImpl {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserMapper userMapper;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    /**
     *
     * @param loginVO 根据表单进行登录验签
     * @return Token 令牌
     */
    public String login(LoginVO loginVO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,loginVO.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user)){
            throw new GlobalException(ResultCodeEnum.FAIL.getCode(),"用户名或者密码错误");
        }
        Authentication authenticate = null;
        try{
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(loginVO.getUsername(),secret + loginVO.getPassword() + user.getSalt());
            authenticate = authenticationManager.authenticate(token);
        }catch (Exception e){
            throw new GlobalException(ResultCodeEnum.FAIL.getCode(),"登录失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        return tokenService.createToken(loginUser);
    }
}
