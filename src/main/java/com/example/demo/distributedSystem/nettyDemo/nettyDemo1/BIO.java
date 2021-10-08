package com.example.demo.distributedSystem.nettyDemo.nettyDemo1;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞模型，一个网络连接对应一个线程
 */
public class BIO {
    static ServerSocket serverSocket = null;
    static {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        while(true){
        //阻塞方法 等待客户端连接
        System.out.println("等待连接");
        Socket socket = serverSocket.accept();
        System.out.println("连接成功");
        byte[] bytes = new byte[1024];
        new Thread(()->{
            while(true){
                try {
                    System.out.println("等待read");
                    int read = 0;
                    if (((read = socket.getInputStream().read(bytes))!=-1)){
                        System.out.println("接受到客户端的信息"+new String(bytes));
                    }
                    System.out.println("read完毕");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        }
    }
}
