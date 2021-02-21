package com.lili.controller;

import com.lili.client.DataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DataFeignController {

    @Autowired
    private DataClient dataClient;

    @RequestMapping("feignGet")
    public String get(){
        return dataClient.getData();
    }
}
