package com.example.user.nettydemo.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.user.nettydemo.R;
import com.example.user.nettydemo.ui.MainActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {

    private String mName;

    private static final String TAG = "TestActivity";
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(v -> {
            LoginMessage loginMessage = new LoginMessage();
            loginMessage.setTerminal(1);
            loginMessage.setDevicetoken("771159829");
            loginMessage.setAccount(TestConfig.account);
            loginMessage.setKey("");
            loginMessage.setAppId("pangjing123");
            loginMessage.setService("gwtuitionroom");
            NetClient.create()
                    .login(loginMessage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(dataResult -> {

                        if (dataResult.getCode() == 1) {
                            return dataResult.getData();
                        }
                        return null;
                    })
                    .subscribe(data -> {
                        if (data != null) {

                            TestConfig.sData = data;
                            String host = "http://"+data.getHost();

                            int wsport = data.getWsport();



                            TestConfig.currentAcount = TestConfig.account;
                            TestConfig.toUser = TestConfig.account2;

                            Intent intent1 = new Intent(TestActivity.this, MainActivity.class);
                            intent1.putExtra("userID",TestConfig.account);
                            TestConfig.userID = TestConfig.account;
                            startActivity(intent1);
                        }


                    }, throwable ->

                    {
                        Log.e(TAG, "onCreate: ", throwable);
                    });
        });

        Button buttonuser2 = (Button) findViewById(R.id.btn_user2);
        buttonuser2.setOnClickListener(v -> {

            LoginMessage loginMessage = new LoginMessage();
            loginMessage.setTerminal(1);
            loginMessage.setDevicetoken("771159829");
            loginMessage.setAccount(TestConfig.account2);
            loginMessage.setKey("");
            loginMessage.setAppId("pangjing");
            loginMessage.setService("gwtuitionroom");
            NetClient.create()
                    .login(loginMessage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(dataResult -> {

                        if (dataResult.getCode() == 1) {
                            return dataResult.getData();
                        }
                        return null;
                    })
                    .subscribe(data -> {
                        if (data != null) {

                            TestConfig.toUser = TestConfig.account;
                            TestConfig.currentAcount = TestConfig.account2;
                            TestConfig.userID = TestConfig.account2;
                            Intent intent1 = new Intent(TestActivity.this, MainActivity.class);
                            intent1.putExtra("userID",TestConfig.account2);
                            startActivity(intent1);
                        }


                    }, throwable -> Log.e(TAG, "onCreate: ",throwable ));
        });

        Button button1 = (Button) findViewById(R.id.btn_send_message);
        button1.setOnClickListener(v -> {




           /* TextMessage textMessage = new TextMessage();
            textMessage.setUserId(TestConfig.account);
//                textMessage.setChatGroupId("huzhanjing");
            String[] strings = new String[]{"huzhanjing"};

            textMessage.setParticipantIds(strings);
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setId(UUID.randomUUID().toString());
//                textMessage.setParticipantIds();
            textMessage.setService("gwtuitionroom");
            textMessage.setType(ChatType.TYPE_SINGLECHAT);
            textMessage.setStatus(MessageSendStatus.STATUS_SENDING);
            textMessage.setUserService("gwtuitionroom");
            textMessage.setContent("hello server");
            HailStormService service = HailStormService.getInstance();
            CountingDownMap<String, Data> sendingMessageIds = service.getSendingMessageIds();
            String messageId = textMessage.getId();

            if (service != null) {

                Integer type = textMessage.getType();
                IncomingMessage incomingMessage = textMessage.toIncomingMessage();
                if (!service.isMessageSending(messageId)) {
                    sendingMessageIds.put(messageId,incomingMessage);
                }
                service.send(incomingMessage);
            }*/

        });
    }
}
