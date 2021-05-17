package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yubo
 * @description 测试注入值
 * @date 2020/6/13
 */
@RestController
public class QureController {
    @Value("小可爱")
    private String name;

    @RequestMapping("/sayok")
    public String sayHello(){
        return "活捉一只"+ name;
    }
}
