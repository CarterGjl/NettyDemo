package com.example.user.nettydemo;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.net.SocketAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * Created by carter on 2018
 */

public class OutBoundHandler extends ChannelOutboundHandlerAdapter {
    private static final String TAG = "OutBoundHandler";
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Logger.d( "write: " + ctx.name()+ new String(((byte[]) msg)));
        super.write(ctx, msg, promise);
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.connect(ctx, remoteAddress, localAddress, promise);

        Logger.d("connect: "+remoteAddress.toString()+"::::::"+localAddress.toString());
    }
}
