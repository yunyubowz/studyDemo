package com.example.demo;

import com.example.demo.model.Man;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    Man man = new Man();

    @Autowired
    ApplicationContext ioc;

    @Test
    void contextLoads() {
        System.out.println(man);
    }

    @Test
    void beantest(){
        System.out.println(ioc.containsBean("helloService"));
    }

}
