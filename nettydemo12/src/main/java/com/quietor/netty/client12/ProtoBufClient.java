package com.quietor.netty.client12;

import com.quietor.protobuf.entity.RequestMsgEntity;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ProtoBufClient {

    public void startClient() throws InterruptedException {
        EventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ProtoBufClientChannelInitializer());

        ChannelFuture channelFuture = bootstrap.connect("localhost", 9999).sync();

        RequestMsgEntity.RequestMsg requestMsg = RequestMsgEntity.RequestMsg.newBuilder()
                                                                            .setCmd("saveUser")
                                                                            .setRequestParam("dafdaf")
                                                                            .build();
        channelFuture.channel().writeAndFlush(requestMsg);
        channelFuture.channel().closeFuture().sync();

        workGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        new ProtoBufClient().startClient();
    }
}
