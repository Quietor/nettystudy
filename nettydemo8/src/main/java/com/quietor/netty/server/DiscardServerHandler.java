package com.quietor.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class DiscardServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        StringBuilder reqContent = new StringBuilder();
        while(buf.isReadable()) {
            reqContent.append((char) buf.readByte());
        }

        System.out.println(reqContent.toString());

        ByteBuf respBuf = ctx.alloc().buffer().writeBytes("hello netty client".getBytes("UTF-8"));
        ctx.writeAndFlush(respBuf);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf reqBuf = ctx.alloc().buffer().writeBytes("hello netty client\\n".getBytes("UTF-8"));
        ctx.writeAndFlush(reqBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
