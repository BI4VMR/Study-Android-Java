package net.bi4vmr.study.service_bind;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_Progress);
        Button btStart = findViewById(R.id.bt_Start);
        Button btStop = findViewById(R.id.bt_Stop);
        Button btBindA = findViewById(R.id.bt_Bind_A);
        Button btBindB = findViewById(R.id.bt_Bind_B);
        Button btUnbindA = findViewById(R.id.bt_Unbind_A);
        Button btUnbindB = findViewById(R.id.bt_Unbind_B);

        // “启动服务”按钮
        btStart.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            startService(i);
        });

        // “停止服务”按钮
        btStop.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            stopService(i);
        });

        // 绑定按钮A
        btBindA.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            // 配置标识符
            i.setType("A");
            bindService(i, connectionA, BIND_AUTO_CREATE);
        });

        // 绑定按钮B
        btBindB.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            // 配置标识符
            i.setType("B");
            bindService(i, connectionB, BIND_AUTO_CREATE);
        });

        // 解绑按钮A
        btUnbindA.setOnClickListener(v -> unbindService(connectionA));

        // 解绑按钮B
        btUnbindB.setOnClickListener(v -> unbindService(connectionB));
    }

    // 服务连接回调A
    private final ServiceConnection connectionA = new ServiceConnection() {

        @SuppressLint("SetTextI18n")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
            Log.d("myapp", "binder:" + binder.hashCode());

            // 启动任务
            binder.start();
            // 设置回调方法，更新界面。
            binder.getService().setCallback(percent -> {
                // 切换至主线程更新UI
                runOnUiThread(() -> textView.setText(percent + "%"));
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("myapp", "onServiceDisconnected()");
        }
    };

    // 服务连接回调A
    private final ServiceConnection connectionB = new ServiceConnection() {

        @SuppressLint("SetTextI18n")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
            Log.d("myapp", "binder:" + binder.hashCode());

            binder.start();
            binder.getService().setCallback(percent -> {
                // 切换至主线程更新UI
                runOnUiThread(() -> textView.setText(percent + "%"));
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("myapp", "onServiceDisconnected()");
        }
    };
}