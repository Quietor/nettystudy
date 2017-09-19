package com.quietor.netty.client;

import com.quietor.netty.server.MyDiscardServerInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DiscardClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new MyDiscardClientInitializer());

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8999).sync();
        channelFuture.channel().closeFuture().sync();

        workerGroup.shutdownGracefully();
    }
}
