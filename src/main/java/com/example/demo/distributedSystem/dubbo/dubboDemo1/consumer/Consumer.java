package com.example.demo.distributedSystem.dubbo.dubboDemo1.consumer;

import com.example.demo.distributedSystem.dubbo.dubboDemo1.framework.ProxyFactory;
import com.example.demo.distributedSystem.dubbo.dubboDemo1.provider.api.DemoService;

public class Consumer {
    public static void main(String[] args) {
        DemoService demoService = ProxyFactory.getProxy(DemoService.class);
        System.out.println(demoService.ConcatString("s1", "23"));
    }
}
