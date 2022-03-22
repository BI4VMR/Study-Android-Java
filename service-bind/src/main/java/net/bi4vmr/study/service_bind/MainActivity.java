package net.bi4vmr.study.service_bind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DownloadService.DownloadBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv_text);
        // 绑定按钮
        Button btBind = findViewById(R.id.bt_bind);
        btBind.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            bindService(i, connection, BIND_AUTO_CREATE);
        });

        // 解绑按钮
        Button btUnbind = findViewById(R.id.bt_unbind);
        btUnbind.setOnClickListener(v -> unbindService(connection));
    }

    private final ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (DownloadService.DownloadBinder) service;
            binder.start();
            binder.getProgress();
            binder.getService().setCallback(data -> Log.i("myapp", "1"));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}