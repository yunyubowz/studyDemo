package com.example.demo.distributedSystem.nettyDemo.nettyDemo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 因为每次需要遍历所有的socketchannel所以我们引入了多路复用器
 */
public class NIODemo2 {
    static ServerSocketChannel serverSocketChannel = null;
    static Selector selector = null;

    static {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9000));
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            //将serverSocket注册到多路复用器上，只监听接受事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        while(true){
            selector.select();//此处为阻塞只有监听事件发生后，才会放行
            /**
             * 获取所有发生监听事件的key
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator  = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到多路复用器上，只监听接受事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("连接成功");
                }
                if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    int len = socketChannel.read(byteBuffer);
                    if(len>0){
                        System.out.println("接受到的消息"+ new String(byteBuffer.array()));
                    }
                    byteBuffer.clear();
                }
                iterator.remove();
            }

        }

    }
}
