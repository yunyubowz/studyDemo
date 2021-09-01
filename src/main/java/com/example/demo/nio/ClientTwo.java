package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientTwo {
    public static void main(String[] args) {
        new Thread(()->startConnect()).start();
    }

    private static void startConnect(){
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",8088);
        SocketChannel socketChannel = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try{
            //打开通道
            socketChannel = SocketChannel.open();
            System.out.println(Thread.currentThread().getName());
            //建立链接
            socketChannel.connect(address);
            while(true){
                byte[] bytes = new byte[1024];
                System.in.read(bytes);
                byteBuffer.put(bytes);
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
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
