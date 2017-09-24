package com.quietor.netty.server10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.Delimiters;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.List;

public class MyServerEncoder extends ByteToMessageCodec<String> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf byteBuf) throws Exception {
        // 指定编码器的采用的编码格式
        byteBuf.writeBytes(s.getBytes("UTF-8"));
        // 每个字符串添加一个编码符
        byteBuf.writeBytes(Delimiters.lineDelimiter()[0]);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }
}
