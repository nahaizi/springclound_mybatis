package com.lili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
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
@EnableConfigServer //启动配置中心服务
public class Application_Config {
    public static void main(String[] args) {
        SpringApplication.run(Application_Config.class);
    }
}
