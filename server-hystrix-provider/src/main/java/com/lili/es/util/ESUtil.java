package com.lili.es.util;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import java.net.UnknownHostException;


public class ESUtil {

    private static volatile RestHighLevelClient client;
    private static final String ip = "192.168.30.168";
    private static final int port = 9200;
    private static final String cluster_name = "docker-cluster";
    /**
     *采用双端检索机制实现客户端为单例模式
     * @param clusterName    你的Elasticsearch集群名称
     * @param hostName       你的Elasticsearch的主机Ip地址
     * @param hostPort       你的Elasticsearch与客户端通信的端口，一般为9300
     * @return TransportClient
     * @throws UnknownHostException
     */
    @SuppressWarnings("resource")
    public static RestHighLevelClient getClient(String clusterName, String hostName, int hostPort) {
        if (client == null) {
            /*synchronized (TransportClient.class) {
                try {
                    client = new PreBuiltTransportClient(Settings.builder().put("cluster.name", clusterName).build())
                            .addTransportAddress(new TransportAddress(InetAddress.getByName(hostName), hostPort));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }*/
        }
        return client;
    }

    public static RestHighLevelClient getClient() {
        if (client == null) {
            synchronized (RestHighLevelClient.class) {

                try {
                    client = new RestHighLevelClient(RestClient.builder( new HttpHost(ip, port, "http")));
                    // 用户认证对象
                    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    // 设置账号密码
                    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("", ""));
                    // 创建rest client对象
                    RestClientBuilder builder = RestClient.builder(new HttpHost(ip, port, "http"))
                            .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
                    client = new RestHighLevelClient(builder);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return client;
    }
}
