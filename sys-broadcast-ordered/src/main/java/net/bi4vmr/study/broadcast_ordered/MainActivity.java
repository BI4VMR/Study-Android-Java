package net.bi4vmr.study.broadcast_ordered;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSend = findViewById(R.id.bt_send);

        // 注册第一个广播接收器
        IntentFilter filter1 = new IntentFilter("net.bi4vmr.MY_BROADCAST_MESSAGE");
        MyReceiver1 receiver1 = new MyReceiver1();
        registerReceiver(receiver1, filter1);

        // 注册第二个广播接收器
        IntentFilter filter2 = new IntentFilter("net.bi4vmr.MY_BROADCAST_MESSAGE");
        // 设置优先级
        filter2.setPriority(100);
        MyReceiver2 receiver2 = new MyReceiver2();
        registerReceiver(receiver2, filter2);

        // 发送按钮
        btSend.setOnClickListener(v -> {
            Intent intent = new Intent("net.bi4vmr.MY_BROADCAST_MESSAGE");
            sendOrderedBroadcast(intent, null);
        });
    }
}