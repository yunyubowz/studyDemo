package com.example.demo.distributedSystem.nettyDemo.nettyDemo3;


import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {
    private int age;
    private String name;
}
