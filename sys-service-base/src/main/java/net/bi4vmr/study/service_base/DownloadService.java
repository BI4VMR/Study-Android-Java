package net.bi4vmr.study.service_base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class DownloadService extends Service {

    // 下载线程
    private Thread downloadThread;

    @Override
    public void onCreate() {
        Log.i("myapp", "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("myapp", "onStartCommand()");
        // 取出外部传入的数据
        String link = intent.getStringExtra("LINK");
        Log.i("myapp", "下载地址：" + link);

        // 在子线程中进行耗时操作
        downloadThread = new Thread(() -> {
            Log.i("myapp", "下载开始");
            try {
                // 休眠5秒，模拟耗时操作。
                Thread.sleep(5000);
                Log.i("myapp", "下载完成");
            } catch (InterruptedException e) {
                Log.i("myapp", "任务终止");
            }
            // 终止服务自身
            stopSelf(startId);
        });
        // 启动任务
        downloadThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("myapp", "onDestroy()");
        // 发送终止信号，停止任务。
        downloadThread.interrupt();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 此服务不需要与调用者通信，因此将返回值设为"null"。
        return null;
    }
}