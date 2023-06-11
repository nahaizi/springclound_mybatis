package com.lili;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @param
 * @author lijunyu
 * @Description: //TODO
 * @return
 * @throws
 * @date ===========================================
 * 修改人：lijunyu，    修改时间：      修改版本：
 * 修改备注：
 * ===========================================
 */
/**
 * 启用服务发现
 * @author lijunyu
 */
@EnableDiscoveryClient
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootGetWayApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringBootGetWayApplication.class);
    }
}
