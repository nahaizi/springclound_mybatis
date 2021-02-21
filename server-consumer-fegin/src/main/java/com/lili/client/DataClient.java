package com.lili.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

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
@FeignClient(value = "TEST-PROVIDER") 	//要调用的服务名称
@RequestMapping("provider")				//和调用服务controller的一致
public interface DataClient {

    //和调用服务controller的一致
    @RequestMapping("get")
    public String getData();
}
