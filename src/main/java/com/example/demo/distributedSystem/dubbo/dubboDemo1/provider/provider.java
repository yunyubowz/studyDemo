package com.example.demo.distributedSystem.dubbo.dubboDemo1.provider;

import com.example.demo.distributedSystem.dubbo.dubboDemo1.framework.protocol.http.HttpServer;
import com.example.demo.distributedSystem.dubbo.dubboDemo1.framework.register.LocalRegister;
import com.example.demo.distributedSystem.dubbo.dubboDemo1.provider.api.DemoService;
import com.example.demo.distributedSystem.dubbo.dubboDemo1.provider.service.DemoServiceImpl;

public class provider {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        LocalRegister.putClass(DemoService.class.getName(), DemoServiceImpl.class);
        httpServer.start("localhost",9080);


    }
}
