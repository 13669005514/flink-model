package com.supermap.model.service.impl;

import com.alibaba.fastjson.JSON;
import com.supermap.model.config.Constant;
import com.supermap.model.pojo.bean.EsResultInfo;
import com.supermap.model.pojo.request.PageInfo;
import com.supermap.model.service.ElasticsearchSerevice;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : zhangfx 2020/1/16 09:24
 * @version : 1.0
 */
@Service
public class ElasticsearchServiceImpl implements ElasticsearchSerevice {

    @Autowired
    private RestHighLevelClient client;

    public Object get(String id) throws IOException {
        GetRequest getRequest = new GetRequest("kibana_sample_data_ecommerce", "o9Imom8Bc7wzLuDw4FIp");
        GetResponse getResponse = client.get(getRequest,RequestOptions.DEFAULT);
        if (getResponse.isExists()) {
            return getResponse.getSourceAsString();
        }
        return null;
    }

    public Map<String, Object> listPage(PageInfo pageInfo) throws Exception{
        //创建检索请求
        SearchRequest searchRequest = new SearchRequest(Constant.masterIndex);
        //创建搜索构建者
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置构建搜索属性
        sourceBuilder.from((pageInfo.getPageIndex()-1)*pageInfo.getPageSize());
        sourceBuilder.size(pageInfo.getPageSize());
        if (StringUtils.isNotEmpty(pageInfo.getKeyWord())){
            BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
            //自定义组合查询
            MatchPhraseQueryBuilder obj_name = QueryBuilders.matchPhraseQuery("obj_name", pageInfo.getKeyWord());
            if (StringUtils.isNotEmpty(pageInfo.getObjType())) {
                MatchQueryBuilder obj_type = QueryBuilders.matchQuery("obj_type", pageInfo.getObjType());
                queryBuilder.must(obj_name).must(obj_type);
            } else {
                queryBuilder.must(obj_name);
            }
            sourceBuilder.query(queryBuilder);
        }
           //传入构建进行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        //处理结果
        RestStatus restStatus = searchResponse.status();
        if (restStatus != RestStatus.OK){
            throw new Exception("搜索错误");
        }
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<EsResultInfo> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            // 转json
            String hitJson = hit.getSourceAsString();
            // 转成java
            EsResultInfo esResultInfo = JSON.parseObject(hitJson, EsResultInfo.class);
            list.add(esResultInfo);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("item",list);
        map.put("total",searchResponse.getHits().getTotalHits().value);
        return map;
    }

    @Override
    public  Map<String, Object> listGroup(PageInfo pageInfo) throws Exception {
        //创建检索请求
        SearchRequest searchRequest = new SearchRequest(Constant.masterIndex);
        //创建搜索构建者
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        if (!StringUtils.isEmpty(pageInfo.getKeyWord())){
            //自定义组合查询
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("obj_name",pageInfo.getKeyWord()));
            sourceBuilder.query(boolQueryBuilder);
        }
        // 分组
        sourceBuilder.aggregation(AggregationBuilders.terms("group").field("obj_type"));
        //传入构建进行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        //处理结果
        RestStatus restStatus = searchResponse.status();
        if (restStatus != RestStatus.OK){
            throw new Exception("搜索错误");
        }
        // 得到分组信息
        Terms terms = searchResponse.getAggregations().get("group");
        //查询分组信息字典表
        @SuppressWarnings("unchecked")
        List<Bucket> buckets = (List<Bucket>) terms.getBuckets();
        Map<String, Object> map = new HashMap<>();
        for (Bucket bucket : buckets) {
            map.put(bucket.getKeyAsString(),bucket.getDocCount());
        }
        return map;
    }
}
