package com.liu.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "全局统一返回结果")
public class R<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public R() {
    }

    /**
     * 构造返回数据的方法
     */
    private static <T> R<T> build(T body, Integer code, String message) {
        R<T> result = new R<>();
        if (body != null) {
            result.setData(body);
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /*public static <T> R<T> success() {
        return R.success(null);
    }*/

    /**
     * 操作成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> success(T data) {
        return build(data, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    /*public static <T> R<T> fail() {
        return fail(null);
    }*/

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getMessage());
    }

    /**
     * 更改返回消息的方法
     * @param msg
     * @return
     */
    public R<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    /**
     * 更改返回状态码的方法
     * @param code
     * @return
     */
    public R<T> code(Integer code){
        this.setCode(code);
        return this;
    }

}
