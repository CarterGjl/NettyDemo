package com.example.user.nettydemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by carter on 2018.
 */

public class InBoundHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = "InBoundHandler";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        super.channelRead(ctx, msg);
    }

}
