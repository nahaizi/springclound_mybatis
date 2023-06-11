package com.lili.provider2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Controller
@RequestMapping("provider")
public class ProviderController {
    private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);
    @GetMapping("get")
    @ResponseBody
    public String getData(){
        logger.info("ProviderController2--提供了数据");
        return "ProviderController2--提供了数据";
    }
}
