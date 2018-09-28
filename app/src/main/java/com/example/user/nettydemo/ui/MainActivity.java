package com.example.user.nettydemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.nettydemo.NettyClient;
import com.example.user.nettydemo.R;
import com.example.user.nettydemo.Test;
import com.example.user.nettydemo.business.OnServerConnectListener;
import com.example.user.nettydemo.service.ServerService;
import com.example.user.nettydemo.test.TestConfig;
import com.orhanobut.logger.Logger;

import org.java_websocket.client.WebSocketClient;

import java.net.InetSocketAddress;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText etTitle;
    private EditText etContent;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etContent = (EditText) findViewById(R.id.etContent);
        tvRes = (TextView) findViewById(R.id.tvRes);
        Intent intent = new Intent(this, ServerService.class);
        startService(intent);

    }

    private WebSocketClient mClient;

    public void connect(View view) {
        new Thread(new Runnable() {


            @Override
            public void run() {


                /*URI uri;
                String url = "ws://" + TestConfig.sData.getHost() + ":" + TestConfig.sData.getWsport();
                try {
                    uri = new URI(url);

                    mClient = new WebSocketClient(uri,new Draft_6455()) {
                        @Override
                        public void onOpen(ServerHandshake handshakedata) {


                            Log.i(TAG, "onOpen: ------------");
                        }

                        @Override
                        public void onMessage(String message) {

                            Log.i(TAG, "onMessage: ========"+message);
                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {

                            Log.i(TAG, "onClose: "+code+":::"+reason+":::"+remote);
                        }

                        @Override
                        public void onError(Exception ex) {


                            Log.e(TAG, "onError: ",ex);
                        }
                    };
                    mClient.connect();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }*/



                /*new InetSocketAddress("127.0.0.1", PORT_NUMBER)*/


                NettyClient.getInstance()
                        .connect(new InetSocketAddress(TestConfig.sData.getHost(), TestConfig
                                .sData.getWsport()), new
                                OnServerConnectListener() {
                            @Override
                            public void onConnectSuccess() {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "connect success!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }

                            @Override
                            public void onConnectFailed() {
                                new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(MainActivity.this, "connect failed!", Toast.LENGTH_SHORT).show());

                            }
                        });
                }
            }).start();

        }

        public void send (View view){


//        mClient.send(etTitle.getText().toString().getBytes());
        Test.ProtoTest protoTest = Test.ProtoTest
                .newBuilder()
                .setId(1)
                .setTitle(etTitle.getText().toString())
                .setContent(etContent.getText().toString())
                .build();
            byte[] bytes = etTitle.getText().toString().getBytes();
            Logger.d(bytes);
            NettyClient.getInstance().send(etTitle.getText().toString(), (Object msg) -> {
            if (msg instanceof Test.ProtoTest) {
                Log.d(TAG, "handleReceive: " + ((Test.ProtoTest) msg).getContent());
                new Handler(Looper.getMainLooper()).post(() -> {
                    tvRes.setText("");
                    Test.ProtoTest test = (Test.ProtoTest) msg;
                    tvRes.setText(String.format("%d\n%s\n%s", test.getId(), test.getTitle(), test.getContent()));
                });

            }
        });
        }
    }
