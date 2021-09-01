package com.example.demo.nioTest;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try{

             ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
             socketChannel = SocketChannel.open();
             socketChannel.connect(new InetSocketAddress("127.0.0.1",8088));
             while(true){
                 byte[] bytes = new byte[1024];
                 System.in.read(bytes);
                 byteBuffer.put(bytes);
                 byteBuffer.flip();
                 socketChannel.write(byteBuffer);
                 byteBuffer.clear();
             }

        }catch (Exception e){

        }finally {
            try{
                if(socketChannel !=null){
                    socketChannel.close();
                }
            }catch (Exception e){

            }
        }
    }

}
