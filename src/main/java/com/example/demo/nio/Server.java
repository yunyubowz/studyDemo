package com.example.demo.nio;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Server implements Runnable {

    private Selector selector;//多路复用器

    private ByteBuffer buffer = ByteBuffer.allocate(1024);//缓冲区

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
    }



    @Override
    public void run() {

    }
}
