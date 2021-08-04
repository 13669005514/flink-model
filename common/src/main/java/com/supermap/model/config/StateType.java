package com.supermap.model.config;

/**
 * @author : zhangfx 2019/8/29/ 17:29
 * @version : 1.0
 */
public enum StateType {

    /**
     * 查询失败
     */
    SELECT_SUCCESS(1,"查询成功"),
    /**
     * 查询失败
     */
    SELECT_ERROR(-1,"查询异常请联系管理员"),

    /**
     * 修改成功
     */
    UPDATE_SUCCESS(1,"修改成功"),
    /**
     * 修改失败
     */
    UPDATE_ERROR(-1,"修改异常请联系管理员"),
    /**
     * 添加成功
     */
    INSERT_SUCCESS(1,"添加成功"),
    /**
     * 添加失败
     */
    INSERT_ERROR(-1,"添加异常请联系管理员"),
    /**
     * 删除成功
     */
    DELETE_SUCCESS(1,"删除成功"),
    /**
     * 删除失败
     */
    DELETE_ERROR(-1,"删除失败请联系管理员"),

    /**
     * 同步数据成功
     */
    SYNC_SUCCESS(1,"同步数据成功"),
    /**
     * 同步数据失败
     */
    SYNC_ERROR(-1,"同步数据失败请联系管理员"),

    /**
     * 分片上传成功
     */
    BLOCK_SUCCESS(1,"文件上传成功"),
    /**
     * 分片上传失败
     */
    BLOCK_ERROR(-1,"文件上传失败请联系管理员");

    /**
     * 响应业务状态
     */
    private Integer responseCD;

    /**
     * 响应消息
     */
    private String responseMsg;

    private StateType(int responseCD, String responseMsg) {
        this.responseCD = responseCD;
        this.responseMsg = responseMsg;
    }

    public String getValue() {
        return this.responseMsg;
    }

    public int getCode() {
        return responseCD;
    }

}
