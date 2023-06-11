package lili.controllor.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbitcustmer")
@Slf4j
public class RabbitCustmerController {
    @Autowired(required = false)
    RabbitTemplate rabbitTemplate;
    @RequestMapping("get/{num}")
    public String get(@PathVariable("num") Integer num) {

        return "";
    }
}
