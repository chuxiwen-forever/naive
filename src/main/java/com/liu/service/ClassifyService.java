package com.liu.service;

import com.liu.entity.Classify;

import java.util.List;

public interface ClassifyService {

    /**
     * 通过类型得到分类
     * @param type 类型种类
     * @return 分类集合
     */
    List<Classify> getAllClassifyByType(Integer type);

    /**
     * 根据需求修改相应的分类信息
     * @param classify 需要修改的分类
     */
    void updateClassify(Classify classify);

    /**
     * 根据id对分类信息进行删除
     * @param id 传入的分类id
     */
    void deleteClassifyById(Long id);

    /**
     * 增加分类信息
     * @param classify 分类信息
     * @return 返回增加主键的分类消息
     */
    Classify insertClassify(Classify classify);

}
