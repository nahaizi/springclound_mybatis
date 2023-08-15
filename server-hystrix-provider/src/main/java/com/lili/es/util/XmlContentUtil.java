package com.lili.es.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.util.concurrent.Monitor;
import com.lili.es.entity.EsbMonitor;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;


public class XmlContentUtil {

    /**
     * Log转化为ES标准数据
     * @param object    要转化的实体类Monitor
     * @return
     */
    public static XContentBuilder getXContentBuilder(Object object){
        if(object.getClass().getName().contains("Monitor")){
            return getEsbMonitorXContentBuilder((EsbMonitor)object);
        }
        return null;
    }
    /**
     * Monitor转化为ES标准数据
     * @param monitor
     * @return
     */
    public static XContentBuilder getEsbMonitorXContentBuilder(EsbMonitor monitor) {
        XContentBuilder xContentBuilder = null;
        try {
            xContentBuilder = XContentFactory.jsonBuilder().startObject()// 标识开始设置值
                    .field("uuid", monitor.getUuid())
                    .field("sys_id", monitor.getSysId())
                    .field("call_sys_id", monitor.getCallSysId())
                    .field("server_ip", monitor.getServerIp())
                    .field("remote_ip", monitor.getRemoteIp())
                    .field("pub_item_name", monitor.getPubItemName())
                    .field("parent_pub_item_name", monitor.getParentPubItemName())
                    .field("session_id", monitor.getSessionId())
                    .field("order_num", monitor.getOrderNum())
                    .field("monitor_id", monitor.getMonitorId())
                    .field("start_time", monitor.getStartTime())
                    .field("duration", monitor.getDuration())
                    .field("status", monitor.getStatus())
                    .field("result_code", monitor.getResultCode())
                    .field("result_desc", monitor.getResultDesc())
                    .field("data_size_in", monitor.getDataSizeIn())
                    .field("data_size_out", monitor.getDataSizeOut())
                    .field("gateway_code", monitor.getGatewayCode())
                    .field("token_id", monitor.getTokenId())
                    .field("client_id", monitor.getClientId())
                    .field("server_id", monitor.getServerId())
                    .endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xContentBuilder;
    }

    /**
     * 生成ES标准数据
     * @param object
     * @return
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static XContentBuilder getXContentBuilderMapping(Class clazz) throws IOException{
        if(clazz.getName().contains("Monitor")){
            return XmlContentUtil.getEsbMonitorXContentBuilderMapping();
        }
        return null;
    }

    /**
     * 生成Monitor的ES标准数据模板
     * @return
     * @throws IOException
     */
    public static XContentBuilder getEsbMonitorXContentBuilderMapping() throws IOException {
        Map<String, Object> keyword = new HashMap<String, Object>();
        keyword.put("type", "keyword");
        keyword.put("ignore_above", 256);
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()//标识开始设置值
                .startObject("properties")
                .startObject("@timestamp").field("type","date").endObject()
                .startObject("@version").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("uuid").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("sys_id").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("call_sys_id").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("server_ip").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("remote_ip").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("pub_item_name").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("parent_pub_item_name").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("session_id").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("order_num").field("type","integer").endObject()
                .startObject("monitor_id").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("start_time").field("type","date").endObject()
                .startObject("duration").field("type","long").endObject()
                .startObject("status").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("result_code").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("result_desc").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("data_size_in").field("type","long").endObject()
                .startObject("data_size_out").field("type","long").endObject()
                .startObject("gateway_code").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("token_id").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("client_id").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .startObject("server_id").field("type","text").startObject("fields").field("keyword",keyword).endObject().endObject()
                .endObject().endObject();
        return mapping;
    }
}
