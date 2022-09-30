package com.liu.service;

import com.liu.DTO.MenuDTO;
import com.liu.VO.MenuVO;
import com.liu.entity.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 查询所以菜单及其其子菜单
     * @return 菜单集合
     */
    List<Menu> selectAllMenu();

    /**
     * 通过标志符获取相应菜单
     * @param name 标志符
     * @return 菜单集合
     */
    MenuDTO getMenuByName(String name);

    /**
     * 更新菜单信息
     * @param menuDTO 标识符
     */
    void updateMenu(MenuDTO menuDTO);

    /**
     * 根据菜单id删除相应菜单
     * @param id 前端传入菜单id
     * @return 返回值为是否可以删除该菜单
     */
    boolean deleteMenu(Long id);

    /**
     * 根据前端表单进行进行添加表单
     * @param menuVO 菜单信息
     * @return 是否可以添加成功
     */
    boolean insertMenu(MenuVO menuVO);
}
