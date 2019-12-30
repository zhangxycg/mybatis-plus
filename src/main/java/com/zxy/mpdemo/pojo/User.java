package com.zxy.mpdemo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: zhangxy
 * @Date: Created in 2019/12/26
 */

@Data
public class User {

    // @TableId(type = IdType.ID_WORKER) // 可以不写，默认就是这种
    private Long id;       // 用户id
    private String name;   // 用户名
    private Integer age;   // 用户年龄
    private String email;  // 用户邮箱


    @TableField(fill = FieldFill.INSERT)
    private Date createTime; // 创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime; // 修改时间

    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version; // 乐观锁版本号

    @TableField(fill = FieldFill.INSERT)
    @TableLogic // 用于逻辑删除的注解
    private Integer deleted; // 逻辑删除标记
}
