package com.example.user.nettydemo;

import android.util.Log;

import com.orhanobut.logger.Logger;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by carter on 2018
 */
//@ChannelHandler.Sharable
public class ServerChannelHandler extends SimpleChannelInboundHandler<byte[]> {
    private static final String TAG = "ServerChannelHandler";

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] protoTest) throws Exception {
        /*Log.d(TAG, "channelRead0: " + channelHandlerContext.name());
        Test.ProtoTest res = Test.ProtoTest.newBuilder()
                .setId(protoTest.getId())
                .setTitle("res" + protoTest.getTitle())
                .setContent("res" + protoTest.getContent())
                .build();*/
        String msg = new String(protoTest);
        Logger.d("server receive======》" + msg);
        channelHandlerContext.writeAndFlush(protoTest).addListener((ChannelFutureListener) channelFuture -> {

            if (channelFuture.isSuccess()) {
                Logger.d("server out=====" + channelFuture.isSuccess());
            }

        });

    }
}
