package com.lili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

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
@EnableFeignClients("com.lili.client") 	//feign接口的地址（上图的client目录）
public class Application_Feign {
    public static void main(String[] args){
        SpringApplication.run(Application_Feign.class);
    }
}
