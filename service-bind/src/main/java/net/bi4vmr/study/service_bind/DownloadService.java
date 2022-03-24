package net.bi4vmr.study.service_bind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class DownloadService extends Service {

    // Binder实例
    private final DownloadBinder binder = new DownloadBinder();

    // 下载线程
    private Thread downloadThread;

    private CallBack callback = null;

    @Override
    public void onCreate() {
        Log.i("myapp", "onCreate()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("myapp", "onBind()");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("myapp", "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("myapp", "onDestroy()");
        // 发送终止信号，停止任务。
        downloadThread.interrupt();
    }

    public interface CallBack {
        void onDataChanged(double percent);
    }

    public void setCallback(CallBack callback) {
        this.callback = callback;
    }

    class DownloadBinder extends Binder {

        private final int TOTAL = 100;
        private int length = 0;

        public void start() {
            // 在子线程中进行耗时操作
            downloadThread = new Thread(() -> {
                Log.i("myapp", "下载开始");
                try {
                    while (length < TOTAL) {
                        length += 10;
                        callback.onDataChanged(getProgress() * 100);
                        // 休眠1秒，模拟耗时操作。
                        Thread.sleep(1000);
                    }
                    Log.i("myapp", "下载完成");
                } catch (InterruptedException e) {
                    Log.i("myapp", "任务终止");
                }
            });
            // 启动任务
            downloadThread.start();
        }

        public double getProgress() {
            return ((double) length / TOTAL);
        }

        public DownloadService getService() {
            return DownloadService.this;
        }
    }
}