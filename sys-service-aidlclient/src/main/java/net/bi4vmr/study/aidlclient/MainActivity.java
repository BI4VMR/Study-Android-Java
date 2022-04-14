package net.bi4vmr.study.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import net.bi4vmr.aidl.IDownloadService;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection connection;
    private boolean isServiceConnected = false;
    private IDownloadService downloadService;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_progress = findViewById(R.id.tv_progress);
        Button btBind = findViewById(R.id.bt_bind);
        Button btUnbind = findViewById(R.id.bt_unbind);
        Button btGetProgress = findViewById(R.id.bt_getprogress);
        Button btAddTask = findViewById(R.id.bt_addtask);
        Button btGetTask = findViewById(R.id.bt_gettask);

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                downloadService = IDownloadService.Stub.asInterface(service);
                // 连接标记位置为"true"
                isServiceConnected = true;
                Log.i("myapp", "连接已就绪");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                // 连接标记位置为"false"
                isServiceConnected = false;
                Log.i("myapp", "连接已断开");
            }
        };

        // 绑定按钮
        btBind.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setPackage("net.bi4vmr.study.aidlserver");
            intent.setAction("net.bi4vmr.aidl.DOWNLOAD");
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        });

        // 解绑按钮
        btUnbind.setOnClickListener(v -> unbindService(connection));

        // 获取进度按钮
        btGetProgress.setOnClickListener(v -> {
            // 根据连接状态标志位确定是否能够访问接口
            if (isServiceConnected) {
                try {
                    tv_progress.setText(downloadService.getProgress() + "%");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                Log.i("myapp", "连接未就绪");
            }
        });

        // 添加任务按钮
        btAddTask.setOnClickListener(v -> {
            // 根据连接状态标志位确定是否能够访问接口
            if (isServiceConnected) {
                try {
                    ItemBean item = new ItemBean("1.txt", "https://test.net/1.txt");
                    downloadService.addTask(item);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                Log.i("myapp", "连接未就绪");
            }
        });

        // 获取任务按钮
        btGetTask.setOnClickListener(v -> {
            // 根据连接状态标志位确定是否能够访问接口
            if (isServiceConnected) {
                try {
                    Log.i("myapp", downloadService.getTask().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                Log.i("myapp", "连接未就绪");
            }
        });
    }
}