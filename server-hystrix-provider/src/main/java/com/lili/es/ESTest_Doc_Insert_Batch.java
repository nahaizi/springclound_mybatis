/*
package com.lili.es;

import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.xcontent.XContentType;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ESTest_Doc_Insert_Batch {
    public static void main(String[] args)  throws  Exception{

        // Create the low-level client
        RestClient restClient = RestClient.builder(new HttpHost("localhost",9200,"http")).build();
// Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

//        DocTestsTransport transport = new DocTestsTransport();
        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);

        JsonFactory f = new MappingJsonFactory();
        JsonParser jp = f.createJsonParser(new File("F:/0905/article.json"));
        JsonToken current;
        current = jp.nextToken();
        if (current != JsonToken.START_OBJECT) {
            System.out.println("Error: root should be object: quiting.");
            return;
        }
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            current = jp.nextToken();
            if ("RECORDS".equals(fieldName)) {
                if (current == JsonToken.START_ARRAY) {

                    while (jp.nextToken() != JsonToken.END_ARRAY) {
                        BulkRequest request=new BulkRequest();
                        JsonNode node = jp.readValueAsTree();
                        String a= delHTMLTag(node.get("html内容").asText());
                        a = a.replaceAll("\\u00A0+", " ");
                        a = a.replaceAll(" ", "");
                        a= delHtmlTags(a);
                        a = a.replace("\\n","");
                        a = a.replace("\\r","");
                        a = a.replace("\\t","");
                        a = a.replace("&nbsp;","");
                        System.out.println(a);
                        request.add(new IndexRequest().index("article_details").id(node.get("资讯ID").asText()).source(XContentType.JSON,"资讯标题",node.get("资讯标题").asText(),"分类(以|分割)",node.get("分类(以|分割)").asText(),"关键词(以,分割)",node.get("关键词(以,分割)").asText(),"html内容",a));
                        BulkResponse response = client.execute(,request);
                    }
                } else {
                    System.out.println("Error: records should be an array: skipping.");
                    jp.skipChildren();
                }
            } else {
                System.out.println("Unprocessed property: " + fieldName);
                jp.skipChildren();
            }
        }
        client.close();
    }

    public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }


    public static String delHtmlTags(String htmlStr) {
        // 定义script的正则表达式，去除js可以防止注入
        String scriptRegex = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        // 定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        // 定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex = "<[^>]+>";
        // 定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\t|\r|\n";
        // 过滤script标签
        htmlStr = htmlStr.replaceAll(scriptRegex, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(styleRegex, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(htmlRegex, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(spaceRegex, "");
        // 过滤
        htmlStr = htmlStr.replace(" ", "");
        // 返回文本字符串
        htmlStr = htmlStr.trim();
        // 去除空格" "
        htmlStr = htmlStr.replaceAll(" ", "");
        return htmlStr;
    }

    public static String getTextFromHtml(String htmlStr){
        //去除html标签
        htmlStr = delHtmlTags(htmlStr);
        //去除空格" "
        htmlStr = htmlStr.replaceAll(" ","");
        return htmlStr;
    }

}


*/
