package com.example.demo.nio;

import org.springframework.expression.spel.ast.Selection;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
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
        while(true){
            try{
                //让多路复用器开始监听
                selector.select();
                System.out.println("接收到");
                //返回所有已经注册到多路复用选择器上的通道的selectionkey
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                //遍历keys
                while(keys.hasNext()){
                    SelectionKey selectionKey = keys.next();
                    keys.remove();
                    if(selectionKey.isValid()){
                        if(selectionKey.isAcceptable()){//如果key是阻塞状态,调用accpet()方法
                            accept(selectionKey);
                        }
                        if(selectionKey.isReadable()){//如果key是可读状态就调用read()方法
                            read(selectionKey);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void accept(SelectionKey selectionKey){
        try{
            //获取服务器通道
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            //执行阻塞方法
            SocketChannel sc = serverSocketChannel.accept();
            //设置阻塞模式为非阻塞
            sc.configureBlocking(false);
            //注册到多路复用选择器上，并设置读取标识
            sc.register(selector,SelectionKey.OP_READ);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void read(SelectionKey selectionKey){
        try{
            //1清空缓冲区的旧数据
            buffer.clear();
            //2获取之前注册的socketChannel通道
            SocketChannel sc = (SocketChannel) selectionKey.channel();
            //将sc的数据放入buffer中
            int count = sc.read(buffer);
            if(count==-1){//==-1表示通道中没有数据
                selectionKey.channel().close();
                selectionKey.cancel();
                return;
            }
            buffer.flip();//将buffer复位position当前位置，limit数据长度 capacity buffer的最大长度
            byte[] bytes = new byte[buffer.remaining()];//获取数据长度
            //将buffer中的数据写入byte[]中
            buffer.get(bytes);
            String data = new String(bytes).trim();
            System.out.println("服务端收到"+data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
