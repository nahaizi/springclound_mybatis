package com.lili.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;

//@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient getRestHighLevelClient() {


        HttpHost[] httpHostarr = new HttpHost[]{new HttpHost("192.168.30.168", 9200, "http"),
                new HttpHost("192.168.30.168", 9201, "http"),
                new HttpHost("192.168.30.168", 9202, "http")};

        return new RestHighLevelClient(RestClient.builder(httpHostarr).setRequestConfigCallback(builder -> {
            return builder.setConnectTimeout(5000 * 1000) // 连接超时（默认为1秒）
                    .setSocketTimeout(6000 * 1000);// 套接字超时（默认为30秒）//更改客户端的超时限制默认30秒现在改为100*1000分钟
        })
        );
    }
}

