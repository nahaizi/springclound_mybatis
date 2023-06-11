package lili.controllor.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/gateWayNacos")
public class GateWayNacosCustormer {
    private final RestTemplate restTemplate;

    @Autowired
    public GateWayNacosCustormer(RestTemplate restTemplate) {this.restTemplate = restTemplate;}


    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        log.info("进入消费====》" + str);
        return restTemplate.getForObject("http://192.168.30.168:8889/cloud-provider/echo/" + str, String.class);
    }
}
