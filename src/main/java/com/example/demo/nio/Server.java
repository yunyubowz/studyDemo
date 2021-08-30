package com.example.demo.nio;

import org.springframework.expression.spel.ast.Selection;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class Server implements Runnable {

    private Selector selector;//多路复用器

    private ByteBuffer buffer = ByteBuffer.allocate(1024);//缓冲区

    public static void main(String[] args) throws IOException {
        new Thread(new Server()).start();
    }

    private Server() throws IOException {
        init(8088);
    }


    private void init(int port) throws IOException {
        //打开多路复用器
        selector = Selector.open();
        //打开服务器通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置服务器通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定地址
        serverSocketChannel.bind(new InetSocketAddress(port));
        //把服务器通道注册到多路复用器上,并监听阻塞状态
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务开始端口为"+port);
    }



    @Override
    public void run() {
        int i = 0;
        while(true){
            try{
                System.out.println("等待连接ing");
                //让多路复用器开始监听
                selector.select();
                //返回所有已经注册到多路复用选择器上的通道的selectionkey
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                System.out.println("遍历"+i+++"次");
                //遍历keys
                while(keys.hasNext()){
                    SelectionKey selectionKey = keys.next();
                    System.out.println(selectionKey);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
