package com.mini.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author liucaiwen
 * 2025/11/26
 */
public class StringHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到消息" + msg);
        ctx.channel().writeAndFlush("+OK\r\n");
    }
}
