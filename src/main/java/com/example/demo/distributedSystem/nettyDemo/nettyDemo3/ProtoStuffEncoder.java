package com.example.demo.distributedSystem.nettyDemo.nettyDemo3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//public class ProtoStuffEncoder extends MessageToByteEncoder<Object> {
////    @Override
////    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
////        // 直接生成序列化对象
////        // 需要注意的是，使用protostuff序列化时，不需要知道pojo对象的具体类型也可以进行序列化时
////        // 在反序列化时，只要提供序列化后的字节数组和原来pojo对象的类型即可完成反序列化
////        byte[] bytes = ProtoStuffUtil.serializer(o);
////        int dataLenth
////    }
//}
