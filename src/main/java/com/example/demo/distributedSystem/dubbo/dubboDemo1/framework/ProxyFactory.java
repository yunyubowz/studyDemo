package com.example.demo.distributedSystem.dubbo.dubboDemo1.framework;

import com.example.demo.distributedSystem.dubbo.dubboDemo1.framework.protocol.http.HttpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory<T> {
    public static <T> T getProxy(final Class interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation();
                invocation.setInterfaceName(interfaceClass.getName());
                invocation.setMethodName(method.getName());
                invocation.setParameterTypes(method.getParameterTypes());
                invocation.setParameters(args);
                String result = HttpClient.send("localhost",9080,invocation);
                return result;
            }
        });
    }
}
