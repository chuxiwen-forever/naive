package com.liu.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO {

    /**
     * 父级id
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
