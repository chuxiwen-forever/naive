package com.liu.security;


import com.alibaba.fastjson.JSON;
import com.liu.utils.R;
import com.liu.utils.ResultCodeEnum;
import com.liu.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足
 */

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        WebUtils.renderString(response, JSON.toJSONString(R.fail(null).message("权限不足，请联系管理员").code(ResultCodeEnum.PERMISSION.getCode())));
    }
}
