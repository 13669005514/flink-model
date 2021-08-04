package com.supermap.model.controller;

import com.supermap.model.pojo.request.PageInfo;
import com.supermap.model.service.ElasticsearchSerevice;
import com.supermap.model.utils.R;
import com.supermap.model.validated.GroupPage;
import com.supermap.model.validated.ListPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhangfx 2020/1/17 10:07
 * @version : 1.0
 */
@RequestMapping("/search")
@RestController
public class ElasticsearchController {

    @Autowired
    private ElasticsearchSerevice elasticsearchSerevice;

    /**
     * 根据关键字查询Es列表信息
     * @param pageInfo 分页信息
     * @return 查询结果
     * @throws Exception 异常信息
     */
    @PostMapping("/listByKeyWord")
    public R listPage(@Validated(ListPage.class)@RequestBody PageInfo pageInfo) throws Exception {

        return R.ok().message("ES全文检索查询结果").data(elasticsearchSerevice.listPage(pageInfo));
    }

    /**
     * 根据关键字统计Es分组统计信息
     * @param pageInfo 分页信息
     * @return 查询结果
     * @throws Exception 异常信息
     */
    @PostMapping("/groupByKeyWord")
    public R listGroup(@Validated(GroupPage.class)@RequestBody  PageInfo pageInfo) throws Exception {
        return R.ok().message("ES全文检索分组统计结果").data("groups",elasticsearchSerevice.listGroup(pageInfo));
    }




}
