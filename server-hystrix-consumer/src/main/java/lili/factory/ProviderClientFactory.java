package lili.factory;

import feign.hystrix.FallbackFactory;
import lili.client.ProviderClient;
import org.springframework.stereotype.Component;

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
@Component
public class ProviderClientFactory implements FallbackFactory<ProviderClient> {

    public ProviderClient create(Throwable cause) {
        return new ProviderClient() {
            public String get(String num) {
                return "Feign调用失败";
            }
        };
    }
}
