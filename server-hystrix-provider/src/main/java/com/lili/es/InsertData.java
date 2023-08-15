/*
package com.lili.es;

import com.lili.es.util.XmlContentUtil;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;

import java.util.List;

public class InsertData {
    */
/**
     * 向指定索引中批量插入数据
     * @param <T>
     * @param indexName 索引名称
     * @param logList Log 的List集合
     * @return
     *//*

    @SuppressWarnings("rawtypes")
    public <T> void addLogListIntoES(String indexName, List<T> logList, Class clazz){
        TransportClient client = this.getClient();
        try {
            BulkRequestBuilder brBulider = client.prepareBulk();
            initIndex(client,indexName,clazz); //初始化索引
            IndexRequest request = null;
            for (Object log : logList) {
                request = client.prepareIndex(indexName,"doc")
                        .setSource(XmlContentUtil.getXContentBuilder(log)).request();
                brBulider.add(request);
            }
            BulkResponse bulkResponse = brBulider.execute().actionGet();
            if(bulkResponse.hasFailures()){
                throw new RuntimeException("日志写入失败");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
*/
