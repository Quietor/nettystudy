package com.quietor.netty.client10;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Netty10Client {

    public void startClient() throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new MyClientInitializer())
                .option(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture channelFuture = bootstrap.connect("localhost", 8888).sync();
        channelFuture.channel().writeAndFlush("你好，服务端");
        channelFuture.channel().closeFuture().sync();

        workerGroup.shutdownGracefully();

    }

    public static void main(String[] args) throws InterruptedException {
        new Netty10Client().startClient();
    }
}
