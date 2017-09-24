package com.quietor.netty.server10;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Netty10Server {

    public void startServer() throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new MyChannelInitializer())
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_BACKLOG, 128);

        ChannelFuture channelFuture = bootstrap.bind(8888).sync();
        channelFuture.channel().closeFuture().sync();

        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        new Netty10Server().startServer();
    }
}
