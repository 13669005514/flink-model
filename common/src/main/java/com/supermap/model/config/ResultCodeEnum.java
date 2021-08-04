package com.supermap.model.config;


import lombok.Getter;


/**
 * @author : zhangfx 2020/1/15 17:42
 * @version : 1.0
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(true,2000,"成功"),
    PARAM_ERROR(false,2001,"参数错误"),
    NULL_POINT(false,2002,"空指针异常"),
    SQL_ERROR(false,2003,"执行SQL异常"),
    FILE_ERROR(false,2004,"文件未找到异常"),
    UNKNOWN_ERROR(false,2020,"未知错误");
    // 响应是否成功
    private Boolean success;
    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
