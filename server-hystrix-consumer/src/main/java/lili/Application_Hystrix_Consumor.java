package lili;

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
@EnableFeignClients(basePackages = "lili.client")
public class Application_Hystrix_Consumor {
    public static void main(String[] args) {
        SpringApplication.run(Application_Hystrix_Consumor.class);
    }
}

