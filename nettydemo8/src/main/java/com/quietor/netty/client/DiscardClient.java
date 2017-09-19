package com.quietor.netty.client;

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class DiscardClient {

    public static final String CLIENT_KEY = "client_key";
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new MyDiscardClientInitializer());

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8999).sync();
        channelFuture.channel().closeFuture().sync();

        Object respContent = channelFuture.channel().attr(AttributeKey.valueOf(CLIENT_KEY)).get();
        System.out.println(respContent);

        workerGroup.shutdownGracefully();
    }
}
