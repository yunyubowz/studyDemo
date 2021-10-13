package com.example.demo.distributedSystem.dubbo.dubboDemo1.provider.service;

import com.example.demo.distributedSystem.dubbo.dubboDemo1.provider.api.DemoService;

public class DemoServiceImpl implements DemoService {

    @Override
    public String ConcatString(String s1, String s2) {
        return s1+s2;
    }
}
