package com.quietor.netty.server12;

import com.quietor.protobuf.entity.RequestMsgEntity;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;

public class ProtoBufServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 强壮msg为RequestMsgEntity
        RequestMsgEntity.RequestMsg requestMsg = (RequestMsgEntity.RequestMsg) msg;
        String cmd = ((RequestMsgEntity.RequestMsg) msg).getCmd();
        System.out.println(cmd);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
