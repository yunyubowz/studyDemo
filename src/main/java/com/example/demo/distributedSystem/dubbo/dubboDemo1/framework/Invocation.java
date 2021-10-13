package com.example.demo.distributedSystem.dubbo.dubboDemo1.framework;

import lombok.Data;

import java.io.Serializable;

@Data
public class Invocation implements Serializable {
    String interfaceName;//接口名
    String methodName;//方法名
    Class[] parameterTypes;//参数类型
    Object[] parameters;//参数
}
