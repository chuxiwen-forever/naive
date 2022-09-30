package com.liu.security;

import com.alibaba.fastjson.JSON;
import com.liu.utils.R;
import com.liu.utils.TokenService;
import com.liu.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if(!ObjectUtils.isEmpty(loginUser)){
            tokenService.delLoginUser(loginUser.getToken());
        }
        WebUtils.renderString(response, JSON.toJSONString(R.success(null).message("退出登录成功")));
    }
}
