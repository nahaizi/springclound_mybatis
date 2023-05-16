package lili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
@EnableFeignClients(basePackages = "lili.client")
public class Application_Hystrix_Consumor {
    public static void main(String[] args) {
        SpringApplication.run(Application_Hystrix_Consumor.class);
    }
    @Bean
    @LoadBalanced //开启负载均衡的功能
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    //修改默认的负载均衡策略
   /* @Bean
    public IRule myRule() {
        //随机策略
        return new RandomRule();
    }*/
}

