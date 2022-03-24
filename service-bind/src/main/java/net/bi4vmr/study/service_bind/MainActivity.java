package net.bi4vmr.study.service_bind;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_progress);
        Button btBind = findViewById(R.id.bt_bind);
        Button btUnbind = findViewById(R.id.bt_unbind);

        // 绑定按钮
        btBind.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            bindService(i, connection, BIND_AUTO_CREATE);
        });

        // 解绑按钮
        btUnbind.setOnClickListener(v -> unbindService(connection));
    }

    private final ServiceConnection connection = new ServiceConnection() {

        @SuppressLint("SetTextI18n")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
            binder.start();
            binder.getService().setCallback(percent -> {
                // 切换至主线程更新UI
                runOnUiThread(() -> textView.setText(percent + "%"));
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}