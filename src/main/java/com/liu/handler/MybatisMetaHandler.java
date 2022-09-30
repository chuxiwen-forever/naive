package com.liu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.liu.utils.UUIDUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MybatisMetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        /**
         * user自动填充
         */
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class,LocalDateTime.now());
        // 头像
        this.strictInsertFill(metaObject,"avatar",String.class,"https://alifei04.cfp.cn/creative/vcg/800/new/VCG21gic13717120.jpg");
        // 状态
        this.strictInsertFill(metaObject,"status",Integer.class,1);
        // 逻辑删除
        this.strictInsertFill(metaObject,"isDeleted",Integer.class,0);
        // 注册以后默认是用户
        this.strictInsertFill(metaObject,"role",Integer.class,0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // user
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
    }
}
