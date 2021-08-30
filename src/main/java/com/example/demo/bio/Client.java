package com.example.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        new Thread(()-> {
            try {
                connect("一号");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try {
                connect("二号");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void connect(String name) throws IOException {
        System.out.println("我是"+name);
        Socket socket = new Socket("127.0.0.1",8088);
        System.out.println("我是"+name+"我连接成功");
        while(true){

        }
    }




}
