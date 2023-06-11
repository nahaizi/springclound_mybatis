package com.lili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@EnableDiscoveryClient // 启用服务发现
@SpringBootApplication
public class Application_Hystrix_Provider {
    public static void main(String[] args) {
        SpringApplication.run(Application_Hystrix_Provider.class);
    }
}

