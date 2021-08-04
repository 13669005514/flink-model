package com.supermap.model.service;

import com.supermap.model.pojo.bean.Test;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 
 * @author : zhangfx 2020/1/15 16:41
 * @version : 1.0
 * 
 */
public interface TestService extends IService<Test>{

    List<Test> geAll();

}
