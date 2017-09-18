package com.quietor.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DiscardServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf writeBuf = ctx.alloc().buffer().writeBytes("Hello netty client111".getBytes("UTF-8"));
        ctx.writeAndFlush(writeBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        while(buf.isReadable()) {
            System.out.print((char) buf.readByte());
            System.out.flush();
        }

        ByteBuf writeBuf = ctx.alloc().buffer().writeBytes("Hello netty client".getBytes("UTF-8"));
        ctx.writeAndFlush(writeBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
