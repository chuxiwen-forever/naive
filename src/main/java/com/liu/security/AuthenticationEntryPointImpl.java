package com.liu.security;


import com.alibaba.fastjson.JSON;
import com.liu.utils.R;
import com.liu.utils.ResultCodeEnum;
import com.liu.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        WebUtils.renderString(response, JSON.toJSONString(R.fail(null).message("用户未登录").code(ResultCodeEnum.LOGIN_AUTH.getCode())));
    }
}
