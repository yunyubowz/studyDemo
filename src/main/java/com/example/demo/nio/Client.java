package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) {
        new Thread(()->startConnect()).start();
        new Thread(()->startConnect()).start();
    }

    private static void startConnect(){
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",8088);
        SocketChannel socketChannel = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try{
            //打开通道
            socketChannel = SocketChannel.open();
            //建立链接
            socketChannel.connect(address);
            while(true){

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(socketChannel!=null){
                try{
                    socketChannel.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
