package lili.controllor;

import lili.client.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("consumer")
public class HystrixConsumerController {

    @Autowired
    private ProviderClient providerClient;

    @GetMapping("get/{num}")
    public String get(@PathVariable("num") String num) {
        String data = providerClient.get(num);
        return data;
    }
}


