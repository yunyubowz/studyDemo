package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ServletComponentScan("com.example.demo.servletTest")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        HashMap<String,String> hasgMap = new HashMap<>();
        hasgMap.put("1","1");

    }

}
