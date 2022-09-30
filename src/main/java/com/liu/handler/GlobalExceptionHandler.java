package com.liu.handler;

import com.liu.exception.GlobalException;
import com.liu.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Date: 2022/6/30
 * @Author: Thousand_Star
 * @Description:
 */
@ControllerAdvice // aop
public class GlobalExceptionHandler {

    // 全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.fail(null).message("服务器出现错误");
    }

    // 自定义异常处理
    @ExceptionHandler({GlobalException.class})
    @ResponseBody
    public R error(GlobalException e) {
        e.printStackTrace();
        return R.fail(null).message(e.getMsg()).code(e.getCode());
    }

}

