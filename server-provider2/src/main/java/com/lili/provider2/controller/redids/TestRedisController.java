package com.lili.provider2.controller.redids;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/testredis")
public class TestRedisController {
    @GetMapping("/setSessionValue")
    @ResponseBody
    public String setredisResult(HttpServletRequest request){
        request.getSession().setAttribute(request.getSession().getId(), "---测试数据---"+request.getRequestURL());

        System.out.println(request.getSession().getId());
        return "set成功，已经存入session域且redis里面也会有值";
    }

    @GetMapping("/getSessionValue")
    @ResponseBody
    public String redisResult(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
        String value = String.valueOf(request.getSession().getAttribute(request.getSession().getId()));

        return "取值成功         :"+value;
    }
}
