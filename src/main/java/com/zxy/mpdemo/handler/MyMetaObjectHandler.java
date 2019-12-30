package com.zxy.mpdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @Author: zhangxy
 * @Date: Created in 2019/12/26
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 在mp执行添加操作的时候运行(会将createTime和updateTime加入到数据库中）
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

        // 添加乐观锁的默认版本是1
        this.setFieldValByName("version", 1, metaObject);

        // 添加逻辑删除的默认值
        this.setFieldValByName("deleted", 0, metaObject);
    }

    /**
     * 在mp执行修改操作的时候运行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
