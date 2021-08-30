package com.example.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        while(true){
            System.out.println("服务端等待连接中ing");
            Socket socket = serverSocket.accept();
            System.out.println("服务端连接成功");
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(socket.getInputStream())) ;
            String requestData = bufferedReader.readLine() ;
            System . out .println( " 接收到客户端的请求数据： " + requestData);
        }

    }
}
