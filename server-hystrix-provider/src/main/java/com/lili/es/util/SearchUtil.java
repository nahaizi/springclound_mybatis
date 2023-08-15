package com.lili.es.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lili.es.entity.BaseLogObj;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author allen
 * @date 2019/11/28-15:01
 */
@Slf4j
public class SearchUtil {
    private List<TermsAggregationBuilder> aggregationBuilders = null;
    private BoolQueryBuilder boolQueryBuilder  = QueryBuilders.boolQuery();
    public SearchUtil(){
        aggregationBuilders = new ArrayList<>();
    }
    /**
     * 添加查询参数(查询类型为And)
     * @param key
     * @param value
     */
    public void setParamAnd(String key, String value){
        boolQueryBuilder.filter(QueryBuilders.termQuery(key, value));
    }
    public void setParam(String key, String value){
        boolQueryBuilder.must(QueryBuilders.matchQuery(key, value));
    }
    /**
     * 添加模糊查询参数(查询类型为And)
     * @param key
     * @param value
     */
    public void setParamLike(String key, String value){
        boolQueryBuilder.filter(QueryBuilders.wildcardQuery(key, "*"+value+"*"));
    }
    /**
     *
     *添加模糊查询参数(查询类型为And)(无分词查询)
     */
    public void setParamAndNotPhrase(String key, String value) {
        this.boolQueryBuilder.filter(QueryBuilders.termQuery(key + ".keyword", value));
    }
    /**
     * 添加查询参数(查询类型为Or)
     * @param key
     * @param value
     */
    public void setParamOr(String key, String value){
        boolQueryBuilder.should(QueryBuilders.termQuery(key,value));
    }

    /**
     * Date类型查询参数及范围设置
     * @param key
     * @param begin
     * @param end
     */
    public void setDateRange(String key, Date begin, Date end){
        String beginStr = DateUtil.getSub8DateStr(begin);
        String endStr = DateUtil.getSub8DateStr(end);
        setRange(key, beginStr, endStr);
    }

    /**
     *  Date类型查询参数及范围设置
     * @param key
     * @param begin
     * @param end
     */
    public void setRange(String key, String begin, String end){
        boolQueryBuilder.must(QueryBuilders.rangeQuery(key).from(begin).to(end));
    }

    /**
     *  Date类型查询参数及范围设置
     * @param key
     * @param begin
     * @param end
     */
    public void setMultiMatchQuery(String key, String... fieldNames){
        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(key,fieldNames));
    }
    /**
     * 添加聚合查询条件
     * @param key
     */
    public void setAggregationParam(String key){
        aggregationBuilders.add(AggregationBuilders.terms(key).field(key + ".keyword"));
    }
    /**
     * 获取ES集群下的所有索引名称
     *
     * @return
     */
    public Set<String> getAllIndex() {
        RestHighLevelClient client = ESUtil.getClient();
        Set<String> indexSet = null;
        try {
            GetAliasesRequest request = new GetAliasesRequest();
            GetAliasesResponse getAliasesResponse =  client.indices().getAlias(request,RequestOptions.DEFAULT);
            Map<String, Set<AliasMetadata>> map = getAliasesResponse.getAliases();
            indexSet = map.keySet();

        }catch(Exception e){
            e.printStackTrace();
        }
        return indexSet;
    }

    /**
     * 获取指定索引下的数据量
     *
     * @param indexName
     * @return
     */
    public long getCountByIndex(String indexName) {
        RestHighLevelClient client = ESUtil.getClient();
        long totalHites = 0l;
        try {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(boolQueryBuilder);
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            totalHites = searchResponse.getHits().getTotalHits().value;
        }catch(Exception e){
            e.printStackTrace();
        }
        return totalHites;
    }
    /**
     * 获取指定索引下的数据量
     *
     * @param indexName
     * @return
     */
    public long matchAllQuery(String indexName) {
        RestHighLevelClient client = ESUtil.getClient();
        long totalHites = 0l;
        try {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(boolQueryBuilder);
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            totalHites = searchResponse.getHits().getTotalHits().value;
        }catch(Exception e){
            e.printStackTrace();
        }
        return totalHites;
    }

    /**
     * 聚合检索
     *
     * @param indexName
     * @return
     */
    public Map<String, Aggregation> getResultByAggreation(String indexName) {
        RestHighLevelClient client = ESUtil.getClient();
        try {


            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(boolQueryBuilder);
            //添加查询条件
            for (TermsAggregationBuilder termsAggregationBuilder : aggregationBuilders) {
                sourceBuilder.aggregation(termsAggregationBuilder);
            }
            searchRequest.source(sourceBuilder);
            SearchResponse actionGet = client.search(searchRequest, RequestOptions.DEFAULT);

            Aggregations aggregations = actionGet.getAggregations();
            Map<String, Aggregation> asMap = aggregations.getAsMap();
            return asMap;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据日志uuid查询单条记录
     * @param indexName
     * @return
     */
    public Object getByUuid(String indexName, Class clazz) {
        RestHighLevelClient client = ESUtil.getClient();
        Object logObj = null;
        try {
            if(!existIndex(client,indexName)){
                return null;
            }


            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(boolQueryBuilder);
            //添加查询条件
            for (TermsAggregationBuilder termsAggregationBuilder : aggregationBuilders) {
                sourceBuilder.aggregation(termsAggregationBuilder);
            }
            searchRequest.source(sourceBuilder);
            searchRequest.searchType(SearchType.DEFAULT);
            searchRequest.scroll(new TimeValue(1000));
            sourceBuilder.size(10);
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            //添加查询条件
            SearchHits hitsFirst = searchResponse.getHits();
            Gson gson = new Gson();
            for (SearchHit hit : hitsFirst) {
                logObj = gson.fromJson(hit.getSourceAsString(), clazz);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logObj;
    }
    /**
     * 分页查询
     * @param indexName
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map pageByIndex(String indexName, Class clazz, Page page) {
        Map<String,Object> resultMap = new HashMap<>(6);
        List<Object> logList = new CopyOnWriteArrayList<>();
        RestHighLevelClient client = ESUtil.getClient();
        try {
            if(!existIndex(client,indexName)){
                return resultMap;
            }
            int pageSize = Integer.parseInt(page.getSize() + "");
            int pageNo = Integer.parseInt(page.getCurrent() + "") ;

            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(boolQueryBuilder);
            //添加查询条件
            for (TermsAggregationBuilder termsAggregationBuilder : aggregationBuilders) {
                sourceBuilder.aggregation(termsAggregationBuilder);
            }
            searchRequest.source(sourceBuilder);

            searchRequest.searchType(SearchType.DEFAULT);
            searchRequest.scroll(new TimeValue(1000));
            sourceBuilder.from(pageNo);
            sourceBuilder.size( pageSize);
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            /*SearchRequestBuilder searchRequestBuilder = client.prepareSearch(new String[]{indexName}).setSearchType(SearchType.DEFAULT).setFrom(pageNo).setSize(pageSize);
            searchRequestBuilder.setQuery(this.boolQueryBuilder);
            SearchResponse searchResponse = (SearchResponse)searchRequestBuilder.execute().actionGet();*/
            SearchHits hitsFirst = searchResponse.getHits();
            Gson gson = new Gson();
            BaseLogObj logObj = null;
            Iterator var13 = hitsFirst.iterator();

            while(var13.hasNext()) {
                SearchHit hit = (SearchHit)var13.next();
                logObj = (BaseLogObj)gson.fromJson(hit.getSourceAsString(), clazz);
                logList.add(logObj);
            }

            long maxNum = this.getCountByIndex(indexName);
            int maxPage = (int)maxNum / pageSize;
            int totalPages = 0;
            if (maxNum % (long)pageSize == 0L) {
                totalPages = maxPage;
            } else {
                ++maxPage;
                totalPages = maxPage;
            }

            resultMap.put("TotalPages",totalPages);
            resultMap.put("TotalRows",maxNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("Results",logList);
        return resultMap;
    }
    /**
     * 查询 List集合
     * @param indexName
     * @return
     */
    public List<Object> listByIndex(String indexName, Class clazz) {
        List<Object> logList = new CopyOnWriteArrayList<>();
        RestHighLevelClient client = ESUtil.getClient();
        try {
            if(!existIndex(client,indexName)){
                return logList;
            }
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(boolQueryBuilder);
            //添加查询条件
            for (TermsAggregationBuilder termsAggregationBuilder : aggregationBuilders) {
                sourceBuilder.aggregation(termsAggregationBuilder);
            }
            sourceBuilder.size(20);
            searchRequest.source(sourceBuilder);

            searchRequest.searchType(SearchType.DEFAULT);
            searchRequest.scroll(new TimeValue(1000));
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            //添加查询条件

            SearchHits hitsFirst = searchResponse.getHits();
            Gson gson = new Gson();
            BaseLogObj logObj = null;
            for (SearchHit hit : hitsFirst) {
                log.info("source =======> " + hit.getSourceAsString());
                logObj = (BaseLogObj) JSON.parseObject(hit.getSourceAsString(), clazz);
                logList.add(logObj);
            }
            //获取总数量
           /* long maxNum = searchResponse.getHits().getTotalHits().value;
            int maxPage = (int) maxNum / 10;//计算总页数
            for (int i = 1; i <= maxPage; i++) {
                //再次发送请求，并使用上次搜索结果的scrollId
               *//* searchResponse = client.scroll(searchResponse.getScrollId())
                        .setScroll(new TimeValue(1000)).execute().actionGet();
                SearchHits searchHits = searchResponse.getHits();
                for (SearchHit hit : searchHits) {
                    logObj = (BaseLogObj)gson.fromJson(hit.getSourceAsString(), clazz);
                    logList.add(logObj.convertToLog());
                }*//*
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logList;
    }

    /**
     * 判断索引是否存在
     * @param client
     * @param indexName
     * @return
     */
    public boolean existIndex(RestHighLevelClient client,String indexName){
        boolean existIndex = false;
        try {
            GetIndexRequest request = new GetIndexRequest(indexName);

            existIndex = client.indices().exists(request, RequestOptions.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return existIndex;
    }
    /**
     * 创建并初始化索引
     *@param clazz    需要创建索引的实体类
     * @param indexName    需要创建的索引名称
     */
    @SuppressWarnings("rawtypes")
    public void initIndex(RestHighLevelClient client, String indexName, Class clazz){
        try {
            if(existIndex(client,indexName)){
                return; //如果该索引存在，则不创建
            }
            //1.创建索引请求
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            //2.执行客户端请求
            CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
            response.isAcknowledged();
            log.info("创建索引{}成功",indexName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
