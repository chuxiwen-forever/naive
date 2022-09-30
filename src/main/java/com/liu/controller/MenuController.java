package com.liu.controller;

import com.liu.DTO.MenuDTO;
import com.liu.VO.MenuVO;
import com.liu.VO.other.DeleteToPostVO;
import com.liu.entity.Menu;
import com.liu.service.MenuService;
import com.liu.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Api("菜单管理")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/list")
    @ApiOperation("获取菜单及其子菜单")
    public R getAllMenu(){
        List<Menu> menuList = menuService.selectAllMenu();
        return R.success(menuList);
    }

    @PostMapping("/info")
    @ApiOperation("获取菜单详情")
    public R getMenuInfo(@RequestBody GetToPostVO vo){
        MenuDTO menuDTO = menuService.getMenuByName(vo.getName());
        if(ObjectUtils.isEmpty(menuDTO)){
            return R.fail(null).message("获取菜单信息失败");
        }
        return R.success(menuDTO).message("获取菜单信息成功");
    }


    @PostMapping("/update")
    @ApiOperation("更新菜单信息")
    public R updateMenu(@RequestBody MenuDTO menuDTO){
        menuService.updateMenu(menuDTO);
        return R.success(null).message("更新成功");
    }

    @PostMapping("/delete")
    @ApiOperation("删除没有子菜单的菜单")
    public R deleteMenu(@RequestBody DeleteToPostVO vo){
        boolean flag = menuService.deleteMenu(vo.getId());
        if (flag){
            return R.success(null).message("删除成功");
        }else {
            return R.fail(null).message("该菜单存在子菜单");
        }
    }

    @PostMapping("/add")
    @ApiOperation("添加新菜单并校验是否有重名")
    public R addMenu(@RequestBody MenuVO menuVO){
        boolean flag = menuService.insertMenu(menuVO);
        if(flag){
            return R.success(null).message("添加成功");
        }
        return R.fail(null).message("路径已经存在，请改正");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class GetToPostVO {
        private String name;
    }
}
