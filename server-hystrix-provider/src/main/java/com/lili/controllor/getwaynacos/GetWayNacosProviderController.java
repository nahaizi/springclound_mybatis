package com.lili.controllor.getwaynacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("gateWayNacos")
public class GetWayNacosProviderController {
    @RequestMapping("/echo/{name}")
    public String getWayTest(@PathVariable("name")String name){
        log.info("进入测试方法====" + name + LocalDateTime.now().toString());
        return "进入测试方法====" + name + LocalDateTime.now().toString();
    }
}
