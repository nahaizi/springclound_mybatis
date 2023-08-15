package com.lili.es;

import com.lili.es.util.SearchUtil;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchData {
    public  void search (String indexName){
        //根据gateway_code聚合，所得结果以gateway_code进行聚合，类似group by
        SearchUtil searchUtilAll = new SearchUtil();
        searchUtilAll.setAggregationParam("gateway_code");
//        addParams(searchUtilAll);
        Map<String, Aggregation> aggreations = searchUtilAll.getResultByAggreation(indexName);
        Terms terms = (Terms)aggreations.get("gateway_code");
        for (Terms.Bucket bucket : terms.getBuckets()) {
            System.out.print("网关编码："+bucket.getKey());
            System.out.println(",数量："+bucket.getDocCount());
        }
//根据gateway_code聚合获取所有成功结果
        SearchUtil searchUtilSuccess = new SearchUtil();
        searchUtilSuccess.setAggregationParam("gateway_code");
        searchUtilSuccess.setParamAnd("status", "1");//1为成功
//        addParams(searchUtilSuccess);
        Map<String, Aggregation> aggreationsSuccess = searchUtilSuccess.getResultByAggreation(indexName);
        Terms termsSuccess = (Terms)aggreationsSuccess.get("gateway_code");
        for (Terms.Bucket bucket : terms.getBuckets()) {
            System.out.print("网关编码："+bucket.getKey());
            System.out.println(",成功数量："+bucket.getDocCount());
        }
    }

/**
     *  全文搜索
     *
     * @param filter
     * @return List<SearchHit>
     * @Excpiton Exception*/


    /*public List<SearchHit> query(Esfilter filter) {
        MultiSearchResponse sr = null;
        //复合查询
        MultiSearchRequest request = new MultiSearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        List<SearchHit> list = new ArrayList<>();

        if (filter.getKeyWord() == null){
            return list;
        }
        //复合查询
        SearchRequest firstSearchRequest = new SearchRequest();
        //bool复合条件查询，should相当于or
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("name", filter.getKeyWord()).analyzer("ik_max_word"))//对指定字段设置ik分词器
                        .should(QueryBuilders.matchQuery("userNickName", filter.getKeyWord()).analyzer("ik_max_word"))
                ).from((filter.getPageNum() - 1) * filter.getPageSize())//设置分页
                .size(filter.getPageSize());
        firstSearchRequest.source(searchSourceBuilder);
        request.add(firstSearchRequest);

        try {
            sr = client.msearch(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //hit对象结果集
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            SearchHits hits = response.getHits();
            for (SearchHit hit: hits){
                list.add(hit);
            }
            System.out.println(response.getHits().getTotalHits());
        }
        return list;
    }*/
}
