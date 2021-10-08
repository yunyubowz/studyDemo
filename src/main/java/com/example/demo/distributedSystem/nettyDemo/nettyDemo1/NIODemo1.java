package com.example.demo.distributedSystem.nettyDemo.nettyDemo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NIODemo1 {
    static ServerSocketChannel serverSocketChannel = null;
    static List<SocketChannel> socketChannelList = new ArrayList<>();

    static {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9000));
            serverSocketChannel.configureBlocking(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel!=null){
                socketChannel.configureBlocking(false);
                socketChannelList.add(socketChannel);
                System.out.println("连接成功");
            }
            Iterator<SocketChannel> socketChannelIterator = socketChannelList.iterator();
            while(socketChannelIterator.hasNext()){
                socketChannel =  socketChannelIterator.next();
                int len = socketChannel.read(byteBuffer);
                if(len>0){
                    System.out.println("接受到的消息"+ new String(byteBuffer.array()));
                }
                byteBuffer.clear();
            }

        }

    }
}
