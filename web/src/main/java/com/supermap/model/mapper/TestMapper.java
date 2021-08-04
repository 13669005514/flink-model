package com.supermap.model.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.supermap.model.pojo.bean.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @author : zhangfx 2020/1/15 16:41
 * @version : 1.0
 * 
 */
@Mapper
public interface TestMapper extends BaseMapper<Test> {

    List<Test> geAll();
}