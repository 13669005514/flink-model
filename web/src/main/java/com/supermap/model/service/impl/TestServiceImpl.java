package com.supermap.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.supermap.model.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.supermap.model.pojo.bean.Test;

import com.supermap.model.service.TestService;
/**
 * 
 * @author : zhangfx 2020/1/15 16:41
 * @version : 1.0
 * 
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService{

    @Autowired
    private TestMapper testMapper;


    @Override
    public List<Test> geAll() {
        return testMapper.geAll();
    }
}
