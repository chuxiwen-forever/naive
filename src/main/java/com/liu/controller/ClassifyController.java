package com.liu.controller;

import com.liu.VO.other.DeleteToPostVO;
import com.liu.entity.Classify;
import com.liu.service.ClassifyService;
import com.liu.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("分类管理接口")
@RequestMapping("/classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;

    @PostMapping("/getByType")
    @ApiOperation("通过类型获取分类")
    public R getClassifyByType(@RequestBody GetToPostVO vo){
        return R.success(classifyService.getAllClassifyByType(vo.getType()));
    }

    @PostMapping("/update")
    @ApiOperation("修改分类信息")
    public R updateClassify(@RequestBody Classify classify){
        classifyService.updateClassify(classify);
        return R.success(null).message("修改成功");
    }

    @PostMapping("/delete")
    @ApiOperation("删除分类信息")
    public R deleteClassify(@RequestBody DeleteToPostVO vo){
        classifyService.deleteClassifyById(vo.getId());
        return R.success(null).message("删除成功");
    }

    @PostMapping("/add")
    @ApiOperation("增加分类消息并返回填有主键的分类消息")
    public R insertClassify(@RequestBody Classify classify){
        classify = classifyService.insertClassify(classify);
        return R.success(classify).message("添加成功");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class GetToPostVO{
        private Integer type;
    }

}
