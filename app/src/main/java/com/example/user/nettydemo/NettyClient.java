package com.example.user.nettydemo;

import android.util.Log;

import com.example.user.nettydemo.business.OnReceiveListener;
import com.example.user.nettydemo.business.OnServerConnectListener;
import com.orhanobut.logger.Logger;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by carter on 2018
 */

public class NettyClient {

    private static final String TAG = "NettyClient";

    private InetSocketAddress mServerAddress;
    private Bootstrap mBootstrap;
    private Channel mChannel;
    private EventLoopGroup mWorkerGroup;
    private OnServerConnectListener onServerConnectListener;
    private Dispatcher mDispatcher;

    private static NettyClient INSTANCE;

    private NettyClient() {
        mDispatcher = new Dispatcher();
    }

    public static NettyClient getInstance() {
        if (INSTANCE == null) {
            synchronized (NettyClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NettyClient();
                }
            }
        }
        return INSTANCE;
    }

    public void connect(final InetSocketAddress socketAddress, OnServerConnectListener onServerConnectListener) {
        if (mChannel != null && mChannel.isActive()) {
            return;
        }
        mServerAddress = socketAddress;
        this.onServerConnectListener = onServerConnectListener;

        if (mBootstrap == null) {
            mWorkerGroup = new NioEventLoopGroup();
            mBootstrap = new Bootstrap();
            mBootstrap.group(mWorkerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder", new ByteArrayDecoder())
                                    .addLast("encoder", new ByteArrayEncoder())
                                    .addLast(new IdleStateHandler(0, 5, 10, TimeUnit.SECONDS))
//                                    .addLast("handler", mDispatcher)
                                    .addLast(new HeartBeatHandler());

                        }
                    }).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        }

        mBootstrap.connect(mServerAddress)
                .addListener(mConnectFutureListener);
//        future.addListener(mConnectFutureListener);
    }

    private ChannelFutureListener mConnectFutureListener = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture pChannelFuture) throws Exception {
            if (pChannelFuture.isSuccess()) {
                mChannel = pChannelFuture.channel();
                if (onServerConnectListener != null) {
                    onServerConnectListener.onConnectSuccess();
                }
                Logger.i("operationComplete: connected!");
            } else {
                if (onServerConnectListener != null) {
                    onServerConnectListener.onConnectFailed();
                }
                Logger.i("operationComplete: connect failed!");
            }
        }
    };

    public synchronized void send(String msg, OnReceiveListener listener) {
        if (mChannel == null) {
            Logger.e("send: channel is null");
            return;
        }

        if (!mChannel.isWritable()) {
            Logger.e("send: channel is not Writable");
            return;
        }

        if (!mChannel.isActive()) {
            Logger.e("send: channel is not active!");
            return;
        }
        mDispatcher.holdListener(msg.getBytes(), listener);
        if (mChannel != null) {
            mChannel.writeAndFlush(msg.getBytes()).addListener((ChannelFutureListener) channelFuture -> {
                if (channelFuture.isSuccess()) {


                    Logger.i("发送是否成功: " + channelFuture.isSuccess());
                } else {

                    Logger.i("发送是否成功: " + channelFuture.isSuccess());
                }
            });
        }

    }
}
