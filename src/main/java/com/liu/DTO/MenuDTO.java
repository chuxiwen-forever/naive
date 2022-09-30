package com.liu.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父 id
     */
    private Long parentId;

    /**
     * 名称
     */
    private String title;

    /**
     * 标识符
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路由
     */
    private String path;

    /**
     * 页面
     */
    private String component;
}
