package com.liu.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDTO {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    /**
     * 电话
     */
    private String phone;

    /**
     * 性别 1-男生 0-女生
     */
    private Integer sex;

    /**
     * 是否是管理员 1-管理员 0-用户
     */
    private Integer role;

    /**
     * 是否在线 1-在线，0-不在线
     */
    private Integer onlineStatus;

}
