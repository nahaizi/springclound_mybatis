package com.lili.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * @author lijunyu
 */
public class LoginController {

    @RequestMapping("doLogin")
    public String doLogin(String name,String pwd){
        System.out.println(name);
        System.out.println(pwd);
        String s = UUID.randomUUID().toString();
        return s;
    }

}
