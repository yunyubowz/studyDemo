package com.example.demo.distributedSystem.dubbo.dubboDemo1.framework.register;

import lombok.Data;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.HashMap;

@Data
public class LocalRegister {
    public static HashMap<String,Class> hashMap = new HashMap<>();

    public static void putClass(String interfaceName,Class className){
        hashMap.put(interfaceName,className);
    }

    public static Class getClass(String interfaceName){
        return hashMap.get(interfaceName);
    }
}
