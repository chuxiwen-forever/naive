package com.liu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liu.DTO.MenuDTO;
import com.liu.VO.MenuVO;
import com.liu.entity.Menu;
import com.liu.mapper.MenuMapper;
import com.liu.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectAllMenu() {

        List<Menu> menuList = menuMapper.selectAllMenu();
        List<Menu> resultMenu = new ArrayList<>();
        for (Menu menu : menuList) {
            if(menu.getParentId() == 0){
                resultMenu.add(menu);
            }
        }
        for (Menu menu : resultMenu) {
            menu.setChildren(getChildMenu(menu.getId(),menuList));
        }
        return resultMenu;
    }

    @Override
    public MenuDTO getMenuByName(String name) {
        MenuDTO menuDTO = null;
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getName,name);
        Menu menu = menuMapper.selectOne(queryWrapper);
        if(!ObjectUtils.isEmpty(menu)){
            menuDTO = new MenuDTO();
            BeanUtils.copyProperties(menu,menuDTO);
        }
        return menuDTO;
    }

    @Override
    public void updateMenu(MenuDTO menuDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDTO,menu);
        menuMapper.updateById(menu);
    }

    @Override
    public boolean deleteMenu(Long id) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId,id);
        List<Menu> menus = menuMapper.selectList(queryWrapper);
        if(menus.size() != 0){
            return false;
        }
        menuMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean insertMenu(MenuVO menuVO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO,menu);
        if(menu.getParentId() != 0){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Menu::getId,menu.getParentId());
            Menu parentMenu = menuMapper.selectOne(queryWrapper);
            menu.setPath(parentMenu.getPath() + menu.getPath());
        }
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getPath,menu.getPath());
        Menu liveMenu = menuMapper.selectOne(queryWrapper);
        if(!ObjectUtils.isEmpty(liveMenu)){
            return false;
        }
        menuMapper.insert(menu);
        return true;
    }


    /**
     * 递归获取层级目录
     * @param parentId 根据父id其子菜单
     * @param menuList 总菜单数据
     * @return 返回完成的菜单
     */
    List<Menu> getChildMenu(Long parentId , List<Menu> menuList){
        List<Menu> childMenu = new ArrayList<>();
        for (Menu menu : menuList) {
            if(parentId.equals(menu.getParentId())){
                childMenu.add(menu);
            }
        }
        for (Menu menu : childMenu) {
            menu.setChildren(getChildMenu(menu.getId(),menuList));
        }
        if(childMenu.size() == 0){
            return new ArrayList<>();
        }
        return childMenu;
    }
}
