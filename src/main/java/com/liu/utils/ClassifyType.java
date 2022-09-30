package com.liu.utils;

import lombok.Data;

public enum ClassifyType {
    SOUND_CODE(1),
    SOFTWARE(2),
    ARTICLE(3);

    ClassifyType(Integer type){
        this.type = type;
    }

    private Integer type;

    public Integer getType() {
        return type;
    }
}
