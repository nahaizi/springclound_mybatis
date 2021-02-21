package com.lili.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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
@SpringBootApplication
@EnableEurekaClient
public class Application_Config_Provider {
    public static void main(String[] args){
        SpringApplication.run(Application_Config_Provider.class);
    }
}

