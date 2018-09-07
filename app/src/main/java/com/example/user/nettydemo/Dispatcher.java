package com.example.user.nettydemo;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.user.nettydemo.business.OnReceiveListener;
import com.orhanobut.logger.Logger;

import java.util.Arrays;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by carter on 2018.
 */

public class Dispatcher extends SimpleChannelInboundHandler<byte[]> {
    private ArrayMap<byte[], OnReceiveListener> receiveListenerHolder;

    public Dispatcher() {
        receiveListenerHolder = new ArrayMap<>();
    }

    public void holdListener(byte[] test, OnReceiveListener onReceiveListener) {
        receiveListenerHolder.put(test, onReceiveListener);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] protoTest)
            throws
            Exception {
        Log.d("","client========>>>>>收到的消息"+ new String(protoTest));
        Log.d("",new String(protoTest));
        if (receiveListenerHolder.containsKey(protoTest)) {
            OnReceiveListener listener = receiveListenerHolder.remove(protoTest);
            if (listener != null) {
                listener.handleReceive(protoTest);
            }
        }
    }

}
