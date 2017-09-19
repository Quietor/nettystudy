package com.quietor.netty.server;

import com.quietor.netty.client.DiscardClientHander;
import com.quietor.netty.server.DiscardServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyDiscardServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new DiscardServerHandler());
    }
}
