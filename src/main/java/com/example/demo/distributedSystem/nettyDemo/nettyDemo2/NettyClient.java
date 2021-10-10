package com.example.demo.distributedSystem.nettyDemo.nettyDemo2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class NettyClient {
    public static void main(String[] args) {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    //pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("_"
                    // .getBytes())));
                    //向pipeline加入解码器
                    pipeline.addLast("decoder", new StringDecoder());
                    //向pipeline加入编码器
                    pipeline.addLast("encoder", new StringEncoder());
                    pipeline.addLast(new NettyClientHandler());
                }
            });
            System.out.println("netty  Client  start");
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",9000).sync();
            Channel channel = channelFuture.channel();
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }
//            //对关闭通道进行监听
//            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            group.shutdownGracefully();
        }
    }
}
