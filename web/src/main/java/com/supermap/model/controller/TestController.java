package com.supermap.model.controller;



import com.supermap.model.pojo.request.PageInfo;
import com.supermap.model.service.ElasticsearchSerevice;
import com.supermap.model.service.TestService;
import com.supermap.model.utils.R;
import com.supermap.model.validated.GroupPage;
import com.supermap.model.validated.ListPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

/**
 * @author : zhangfx 2020/1/14 17:21
 * @version : 1.0
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ElasticsearchSerevice elasticsearchSerevice;


    @GetMapping("/hello2")
    public R sayHello2() throws IOException {
        return R.ok().message("数据库测试查询结果").data("items",testService.geAll());
    }

    @PostMapping("/hello3")
    public R sayHello3(@Validated(ListPage.class)@RequestBody  PageInfo pageInfo) throws Exception {
        return R.ok().message("ES全文检索分页查询结果").data(elasticsearchSerevice.listPage(pageInfo));
    }

    @PostMapping("/hello4")
    public R sayHello4(@Validated(GroupPage.class)@RequestBody  PageInfo pageInfo) throws Exception {
        return R.ok().message("ES全文检索分组查询结果").data("groups",elasticsearchSerevice.listGroup(pageInfo));
    }

}
