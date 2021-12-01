package com.lili.controllor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("provider")
public class HystrixProviderController {

    @GetMapping("get/{num}")
    public String get(@PathVariable("num") Integer num) {
        if (num == 123) {
            throw new RuntimeException("FAILED");
        }
        return num + "==SUCCEED";
    }
}

