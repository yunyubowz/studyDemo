package com.example.demo.nioTest;

import org.springframework.expression.spel.ast.Selection;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * nio的服务端
 */
public class Server implements Runnable {

    private Selector selector = null;//多路复用器
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//缓冲区

    public Server(int port){
        init(port);
    }

    public static void main(String[] args) {
        new Thread(new Server(8088)).start();
    }

    //初始化服务
    private void init(int port){
        try{
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            SocketAddress socketAddress = new InetSocketAddress(port);
            serverSocketChannel.bind(socketAddress);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务开始服务的端口为"+port);
        }catch (Exception e){
           e.printStackTrace();
        }finally {

        }

    }


    @Override
    public void run() {
        while(true){
        try{
                System.out.println("多路复用器开始监听");
                selector.select();//多路复用器开始监听
                System.out.println("关键字的个数"+selector.selectedKeys().size());
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                System.out.println(selectionKeys);
                while(selectionKeys.hasNext()){
                    SelectionKey  selectionKey =  selectionKeys.next();
                    selectionKeys.remove();
                    if(selectionKey.isValid()){//如果key是有效的
                        System.out.println("Acceptable---->"+selectionKey.isAcceptable());
                        System.out.println("Readable---->"+selectionKey.isReadable());
                        if(selectionKey.isAcceptable()){//如果key是阻塞状态就调用accpt方法
                            accpet(selectionKey);
                        }
                        if(selectionKey.isReadable()){//如果key是可读状态就调用read方法
                            read(selectionKey);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {

            }
        }

    }

    private void accpet(SelectionKey selectionKey){
        try{
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel  = serverSocketChannel.accept();
            System.out.println("连接成功一个");
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_READ);
        }catch (Exception e){
            e.printStackTrace();
        } finally {

        }

    }

    private void read(SelectionKey selectionKey){
        SocketChannel socketChannel = null;
        try{
            byteBuffer.clear();
            socketChannel = (SocketChannel) selectionKey.channel();
            int count = socketChannel.read(byteBuffer);
            System.out.println(count);
            if(count == -1){//如果数据长度等于-1表示通道中没有数据
                socketChannel.close();
                selectionKey.cancel();
                return;
            }
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];//获取数据长度
            byteBuffer.get(bytes);
            String s = new String(bytes).trim();
            System.out.println("服务端接收到"+s);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}
