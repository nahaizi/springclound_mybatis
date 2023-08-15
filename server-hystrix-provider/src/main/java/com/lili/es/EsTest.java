package com.lili.es;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public class EsTest {
    private String ip = "192.168.30.168";
    private int port = 9300;
    private String cluster_name = "docker-cluster";

    //插入数据
    @Test
    public void test2() throws IOException {
        //1、指定es集群  cluster.name 是固定的key值，my-application是ES集群的名称
        Settings settings = Settings.builder().put("cluster.name", cluster_name).build();
        //2.创建访问ES服务器的客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                //获取es主机中节点的ip地址及端口号(以下是单个节点案例)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(ip), port));
        //将数据转换成文档的格式（后期可以使用java对象，将数据转换成json对象就可以了）
            XContentBuilder doContentBuilder= XContentFactory.jsonBuilder()
                .startObject()
                .field("id", "5") //字段名 ： 值
                .field("title", "java设计模式之装饰模式5")
                .field("content", "在不必改变原类文件和使用继承的情况下，动态地扩展一个对象的功能5")
                .field("postdate", "2018-05-25")
                .field("url", "https://www.cnblogs.com/chenyuanbo/")
                .endObject();
        //添加文档  index1:索引名 blog:类型 10:id
//.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE) 代表插入成功后立即刷新，因为ES中插入数据默认分片要1秒钟后再刷新


        IndexResponse response = client.prepareIndex("index1","blog","10")
                .setSource(doContentBuilder).setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
        System.out.println(response.status());
        //打印出CREATED 表示添加成功
    }

    //删除文档
    @Test
    public void test3() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", cluster_name).build();
        //2.创建访问ES服务器的客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                //获取es主机中节点的ip地址及端口号(以下是单个节点案例)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(ip), port));
        DeleteResponse response = client.prepareDelete("index1","blog","10").get();
        System.out.println(response.status());
        //控制台打印出OK代表删除成功
    }


    //修改数据（指定字段进行修改)
    @Test
    public void test4() throws IOException, InterruptedException, ExecutionException {
        Settings settings = Settings.builder().put("cluster.name", cluster_name).build();
        //2.创建访问ES服务器的客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                //获取es主机中节点的ip地址及端口号(以下是单个节点案例)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(ip), port));
        UpdateRequest request = new UpdateRequest();
        request.index("index1") //索引名
                .type("blog") //类型
                .id("10")//id
                .doc(
                        XContentFactory.jsonBuilder()
                                .startObject()
                                .field("title", "单例设计模式")//要修改的字段 及字段值
                                .endObject()
                );
        UpdateResponse response= client.update(request).get();
        System.out.println(response.status());
        //控制台出现OK 代表更新成功
    }

    //upsert 修改用法：修改文章存在，执行修改，不存在则执行插入
    @Test
    public void test5() throws IOException, InterruptedException, ExecutionException {
        Settings settings = Settings.builder().put("cluster.name", cluster_name).build();
        //2.创建访问ES服务器的客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                //获取es主机中节点的ip地址及端口号(以下是单个节点案例)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(ip), port));
        IndexRequest request1 = new IndexRequest("index2","blog","8").source(
                XContentFactory.jsonBuilder()
                        .startObject()
                        .field("id", "2") //字段名 ： 值
                        .field("title", "工厂模式")
                        .field("content", "静态工厂，实例工厂")
                        .field("postdate", "2018-05-20")
                        .field("url", "https://www.cnblogs.com/chenyuanbo/")
                        .endObject()
        );
        UpdateRequest request2 = new UpdateRequest("index2","blog","8").doc(
                XContentFactory.jsonBuilder().startObject()
                        .field("title", "设计模式")
                        .endObject()
        ).upsert(request1);
        UpdateResponse response = client.update(request2).get();
        System.out.println(response.status());
    }

    //query 查询用法：
    @Test
    public void test6() throws IOException, InterruptedException, ExecutionException {
        Settings settings = Settings.builder().put("cluster.name", cluster_name).build();
        //2.创建访问ES服务器的客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                //获取es主机中节点的ip地址及端口号(以下是单个节点案例)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(ip), port));
        IndexRequest request1 = new IndexRequest("index2","blog","8").source(
                XContentFactory.jsonBuilder()
                        .startObject()
                        .field("id", "2") //字段名 ： 值
                        .field("title", "工厂模式")
                        .field("content", "静态工厂，实例工厂")
                        .field("postdate", "2018-05-20")
                        .field("url", "https://www.cnblogs.com/chenyuanbo/")
                        .endObject()
        );
        UpdateRequest request2 = new UpdateRequest("index2","blog","8").doc(
                XContentFactory.jsonBuilder().startObject()
                        .field("title", "设计模式")
                        .endObject()
        ).upsert(request1);
        UpdateResponse response = client.update(request2).get();
        System.out.println(response.status());
    }


}
