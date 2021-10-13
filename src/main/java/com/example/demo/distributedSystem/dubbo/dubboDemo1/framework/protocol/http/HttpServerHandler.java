package com.example.demo.distributedSystem.dubbo.dubboDemo1.framework.protocol.http;

import com.example.demo.distributedSystem.dubbo.dubboDemo1.framework.Invocation;
import com.example.demo.distributedSystem.dubbo.dubboDemo1.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void hander(HttpServletRequest request, HttpServletResponse response){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(request.getInputStream());
            Invocation invocation = (Invocation)inputStream.readObject();
            String interfaceName = invocation.getInterfaceName();
            String methodName = invocation.getMethodName();
            Class[] parameterTypes = invocation.getParameterTypes();
            Object[] parameters = invocation.getParameters();
            System.out.println(LocalRegister.hashMap);
            Class c = LocalRegister.getClass(interfaceName);
            Method method = c.getMethod(methodName,parameterTypes);
            String result = (String)method.invoke(c.newInstance(),parameters);
            IOUtils.write(result,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
