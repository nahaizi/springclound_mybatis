package lili.client;

//import lili.factory.ProviderClientFactory;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
//添加一个后备工厂，在失败时使用
//@FeignClient(value = "TEST-HYSTRIX-PROVIDER", fallbackFactory = ProviderClientFactory.class)
@RequestMapping("provider")
public interface ProviderClient {

    @GetMapping("get/{num}")
    public String get(@PathVariable("num") String num);
}

