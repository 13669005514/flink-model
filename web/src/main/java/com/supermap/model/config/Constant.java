package com.supermap.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 常用常量定义
 * @author : zhangfx 2020/1/16 20:58
 * @version : 1.0
 */
@Configuration
public class Constant {

    /**
     * Es数据索引
     */
    public static String masterIndex;

    @Value("${elasticsearch.masterIndex}")
    public void setMasterIndex(String masterIndex) {
        Constant.masterIndex = masterIndex;
    }
}
