/*
package com.lili.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.lili.config.GatewayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

@Component
@Slf4j
@DependsOn({"GatewayConfig"}) // 依赖于gatewayConfig bean
public class DynamicRouteServiceImplByNacos {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;


    private ConfigService configService;

    @PostConstruct
    public void init() {
        try{
            configService = initConfigService();
            if(configService == null){
                return;
            }
            //从nacos拿初始配置
            String configInfo = configService.getConfig(GatewayConfig.NACOS_ROUTE_DATA_ID, GatewayConfig.NACOS_ROUTE_GROUP, GatewayConfig.DEFAULT_TIMEOUT);
            List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
            for(RouteDefinition definition : definitionList){
                dynamicRouteService.add(definition);
            }
        } catch (Exception e) {
        }
        //注册监听，nacos配置有更新的话同步更新一下配置
        dynamicRouteByNacosListener(GatewayConfig.NACOS_ROUTE_DATA_ID,GatewayConfig.NACOS_ROUTE_GROUP);
    }

    */
/**
     * 监听Nacos下发的动态路由配置
     * @param dataId
     * @param group
     *//*

    public void dynamicRouteByNacosListener (String dataId, String group){
        try {
            configService.addListener(dataId, group, new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
                    dynamicRouteService.updateList(definitionList);
                }
                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
        }
    }

    */
/**
     * 初始化网关路由 nacos config
     * @return
     *//*

    private ConfigService initConfigService(){
        try{
            Properties properties = new Properties();
            properties.setProperty("serverAddr",GatewayConfig.NACOS_SERVER_ADDR);
            properties.setProperty("namespace",GatewayConfig.NACOS_NAMESPACE);
            return configService= NacosFactory.createConfigService(properties);
        } catch (Exception e) {
            return null;
        }
    }

}
*/
