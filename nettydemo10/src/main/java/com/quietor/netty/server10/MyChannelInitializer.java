package com.quietor.netty.server10;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 给server端添加解码器
        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
        socketChannel.pipeline().addLast(new StringDecoder());
        // 给server端添加一个编码器
        socketChannel.pipeline().addLast(new MyServerEncoder());
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
