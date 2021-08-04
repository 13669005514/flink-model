package com.supermap.model.service;

import com.supermap.model.pojo.request.PageInfo;

import java.io.IOException;
import java.util.Map;

/**
 * ES查询服务操作
 * @author : zhangfx 2020/1/16 09:23
 * @version : 1.0
 */
public interface ElasticsearchSerevice {

    public Object get(String id) throws IOException;


    /**
     * 根据关键字和类别分页查询信息
     * @param pageInfo 关键字信息
     * @return 查询结果
     * @throws Exception 异常信息
     */
    public Map<String, Object> listPage(PageInfo pageInfo)throws Exception;

    /**
     * 根据关键字分组查询总数
     * @param pageInfo 关键字信息
     * @return 查询结果
     * @throws Exception 异常信息
     */
    public Map<String, Object> listGroup(PageInfo pageInfo)throws Exception;

}
