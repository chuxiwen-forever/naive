package com.liu.service.impl;

import com.liu.entity.Classify;
import com.liu.mapper.ClassifyMapper;
import com.liu.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;

    @Override
    public List<Classify> getAllClassifyByType(Integer type) {
        List<Classify> classifies = classifyMapper.selectAllByType(type);
        List<Classify> resultList = new ArrayList<>();
        for (Classify classify : classifies) {
            Long parentId = classify.getParentId();
            if(parentId == 0){
                resultList.add(classify);
            }else{
                for (Classify parentClassify : classifies) {
                    if(parentClassify.getParentId() == 0 && parentClassify.getId().equals(parentId)){
                        String router = parentClassify.getRouter() + classify.getRouter();
                        classify.setRouter(router);
                        resultList.add(classify);
                    }
                }
            }
        }
        return resultList;
    }

    @Override
    public void updateClassify(Classify classify) {
        classifyMapper.updateById(classify);
    }

    @Override
    public void deleteClassifyById(Long id) {
        classifyMapper.deleteById(id);
    }

    @Override
    public Classify insertClassify(Classify classify) {
        classifyMapper.insert(classify);
        return classify;
    }

}
