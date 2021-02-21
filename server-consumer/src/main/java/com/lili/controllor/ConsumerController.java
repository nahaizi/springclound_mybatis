package com.lili.controllor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping("consumer")
public class ConsumerController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);
    //资源路径
    private final String url = "http://192.168.20.118:8001/provider/get";

    @RequestMapping("get")
    public String get(){
        RestTemplate template = new RestTemplate();
        String data = template.getForObject(url, String.class);
        data = "ConsumerController333" + data;
        logger.info(data);
        return data;
    }


    @RequestMapping("/say")
    public String say(){
        return "I love qianqianqqqqq";
    }

}