package com.lili.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lijunyu
 */
@EnableDiscoveryClient

@SpringBootApplication
public class EsWebApplications {
    public static void main(String[] args) {
        SpringApplication.run(EsWebApplications.class);
    }
}
