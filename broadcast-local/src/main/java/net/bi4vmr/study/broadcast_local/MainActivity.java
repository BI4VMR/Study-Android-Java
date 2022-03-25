package net.bi4vmr.study.broadcast_local;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private LocalBroadcastManager manager;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSend = findViewById(R.id.bt_send);

        // 获取LocalBroadcastManager实例
        manager = LocalBroadcastManager.getInstance(this);
        // 注册广播接收器
        IntentFilter filter = new IntentFilter("net.bi4vmr.MY_BROADCAST");
        receiver = new MyReceiver();
        manager.registerReceiver(receiver, filter);

        // 发送按钮
        btSend.setOnClickListener(v -> {
            Intent intent = new Intent("net.bi4vmr.MY_BROADCAST");
            manager.sendBroadcast(intent);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 注销广播接收器
        manager.unregisterReceiver(receiver);
    }

    static class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("myapp", "MyReceiver接收到广播");
        }
    }
}