package com.quietor.netty.client10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.List;

public class MyClientEncoder extends ByteToMessageCodec<String> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(s.getBytes("UTF-8"));
        byteBuf.writeBytes(Delimiters.lineDelimiter()[0]);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }
}
