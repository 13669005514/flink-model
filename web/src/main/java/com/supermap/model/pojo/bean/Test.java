package com.supermap.model.pojo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @author : zhangfx 2020/1/15 16:41
 * @version : 1.0
 * 
 */
@Data
@TableName(value = "test")
public class Test {

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "username")
    private String name;

    @TableField(value = "password")
    private String password;
}