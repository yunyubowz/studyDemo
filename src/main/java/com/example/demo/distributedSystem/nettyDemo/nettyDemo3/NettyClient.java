package com.example.demo.distributedSystem.nettyDemo.nettyDemo3;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class NettyClient {

    public static ChannelFuture channelFuture = null;
    public static Bootstrap bootstrap = new Bootstrap();
    public static void main(String[] args) throws Exception {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap

            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) // 使用 NioSocketChannel 作为客户端的通道实现
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
//                            channel.pipeline().addLast("decode", new ObjectDecoder(ClassResolvers.weakCachingResolver(this.getClass().getClassLoader())));
//                            channel.pipeline().addLast("encoder",new ObjectEncoder());
                            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            //加入处理器
                            channel.pipeline().addLast(new NettyClientHandlerkk());
                        }
                    });
            System.out.println("netty client start");
            //启动客户端去连接服务器端
            connect(bootstrap);
            while(true){
                ByteBuf buf = Unpooled.copiedBuffer("Heartbeat Packet\n", CharsetUtil.UTF_8);
                channelFuture.channel().writeAndFlush(buf);
                Thread.sleep(2000);
            }
            //对关闭通道进行监听
            //channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    public static void connect(Bootstrap bootstrap){
        channelFuture = bootstrap.connect("127.0.0.1", 9000);
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(!channelFuture.isSuccess()){
                    channelFuture.channel().eventLoop().schedule(()->{
                        connect(bootstrap);
                    },3, TimeUnit.SECONDS);
                    System.out.println("正在尝试重新连接");
                }else {
                    System.out.println("连接成功");
                }
            }
        });
    }

static class NettyClientHandlerkk extends ChannelInboundHandlerAdapter {

    /**
     * 当客户端连接服务器完成就会触发该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        User user = new User();
//        user.setAge(18);
//        user.setName("于波");
//        ctx.writeAndFlush(user);
    }

    //当通道有读取事件时会触发，即服务端发送数据给客户端
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("收到服务端的消息:" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务端的地址： " + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("运行中断开重连。。。");
        connect(bootstrap);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
}
