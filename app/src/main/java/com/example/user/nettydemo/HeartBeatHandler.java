package com.example.user.nettydemo;

import com.orhanobut.logger.Logger;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {

        String ms = new String(((byte[]) msg));
        Logger.d("client receive=========>" + ms);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            //准备长连接
            Logger.i("============prepare heart beat===========>>");
            ctx.executor().execute(() -> handleIdleEvent(ctx, ((IdleStateEvent) evt)));
        }
    }

    private void handleIdleEvent(ChannelHandlerContext ctx, IdleStateEvent event) {

        IdleState state = event.state();
        switch (state) {
            case ALL_IDLE:
                break;
            case READER_IDLE:
                break;
            case WRITER_IDLE:
                ctx.writeAndFlush("ping".getBytes()).addListener((ChannelFutureListener) channelFuture -> {

                    if (channelFuture.isSuccess()) {
                        Logger.i("============sending ping to server===========");
                    }else {
                        Logger.i("============sending ping to server failed===========");
                    }
                });

                break;
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);


        // TODO: 2018/9/7 重连等操作
    }
}
