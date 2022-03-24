package net.bi4vmr.study.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import net.bi4vmr.aidl.IDownloadService;

public class DownloadService extends Service {

    private final IDownloadService.Stub stub = new IDownloadService.Stub() {

        private final int TOTAL = 100;
        private int length = 0;

        @Override
        public void start(String URL) {
            Thread thread = new Thread(() -> {
                try {
                    Log.i("DownloadService", "开始下载:" + URL);
                    while (length < TOTAL) {
                        length += 10;
                        // 休眠1秒，模拟耗时操作。
                        Thread.sleep(1000);
                    }
                    Log.i("DownloadService", "下载完成");
                } catch (InterruptedException e) {
                    Log.i("DownloadService", "任务终止");
                }
            });
            thread.start();
        }

        @Override
        public double getProgress() {
            return (double) length / TOTAL;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}