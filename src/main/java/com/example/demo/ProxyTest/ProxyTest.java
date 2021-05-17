package com.example.demo.ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest implements MoveInfter{
    public static void main(String[] args) {
        ProxyTest proxyTest = new ProxyTest();
        MoveInfter moveInfter = (MoveInfter) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{MoveInfter.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
                System.out.println("開始啦");
                method.invoke(proxyTest,args);
                System.out.println("結束啦");
                return null;
            }
        });
        moveInfter.move();

    }

    @Override
    public void move() {
        System.out.println("開始ing");
    }

}



interface MoveInfter{
    void move();
}