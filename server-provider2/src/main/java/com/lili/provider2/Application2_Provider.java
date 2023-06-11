package provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 5)
@ComponentScan(basePackages = "com.lili.provider2.controller")
public class Application2_Provider {
    public static void main(String[] args){
        SpringApplication.run(Application2_Provider.class);
    }
}
