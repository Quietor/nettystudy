package com.quietor.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

@ChannelHandler.Sharable
public class DiscardClientHander extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf respBuf = (ByteBuf) msg;

        StringBuilder respBuilder = new StringBuilder();
        while (respBuf.isReadable()) {
            respBuilder.append((char) respBuf.readByte());
        }

        ctx.channel().attr(AttributeKey.valueOf(DiscardClient.CLIENT_KEY)).set(respBuilder.toString());
        ctx.channel().close();
        ctx.close();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
