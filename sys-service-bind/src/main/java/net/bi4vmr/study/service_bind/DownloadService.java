package net.bi4vmr.study.service_bind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Name          : DownloadService
 * Author        : BI4VMR
 * Date          : 2022-04-18 11:23
 * Description   : 启动服务示例
 */
public class DownloadService extends Service {

    // 下载线程
    private Thread downloadThread;
    // 回调接口的实现类，用于向客户端反馈结果。
    private CallBack callback;

    @Override
    public void onCreate() {
        Log.d("myapp", "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myapp", "onStartCommand()");
        return START_NOT_STICKY;
    }

    /**
     * Name        : onBind()
     * Description : 外部组件执行"bindService()"绑定服务时，本方法被调用。
     * 服务运行过程中，如果同一个组件多次请求绑定，"onBind()"方法只会在初次绑定时执行，后续系统直接向调用者返回之前
     * 创建的Binder实例。
     * 系统判断绑定服务的请求是否来自“同一个组件”的依据是：Intent属性是否相同，包括Action和Type，但不包括Extra。
     * 即使是不同APK中的组件，若使用具有相同属性的Intent绑定某个Service，系统也会返回同一个Binder实例。
     * 如果我们需要获取不同的Binder实例，可以使用"setAction()"和"setType()"方法为请求Intent设置不同的属性。
     *
     * @param intent 绑定服务的外部组件创建的Intent
     * @return 自定义Binder类的实例
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("myapp", "onBind()");
        return new DownloadBinder();
    }

    /**
     * Name        : onUnbind()
     * Description : 当所有绑定服务的组件都调用"unbindService()"解绑后，此方法被调用。
     *
     * @param intent 绑定服务的外部组件创建的Intent
     * @return 默认值为"false"，参考"onRebind()"方法的注释。
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("myapp", "onUnbind()");
        return false;
    }

    /**
     * Name        : onRebind()
     * Description : 如果"onUnbind()"返回"true"，且服务已经被所有组件解绑，再次被绑定时将会执行此方法。
     * 如果"onUnbind()"返回"false"，且服务已经被所有组件解绑，再次被绑定时不会调用任何生命周期方法。
     *
     * @param intent 绑定服务的外部组件创建的Intent
     */
    @Override
    public void onRebind(Intent intent) {
        Log.d("myapp", "onRebind()");
    }

    @Override
    public void onDestroy() {
        Log.d("myapp", "onDestroy()");
        // 发送终止信号，停止任务。
        if (downloadThread != null) {
            downloadThread.interrupt();
        }
    }

    // 回调接口
    public interface CallBack {
        void onDataChanged(double percent);
    }

    public void setCallback(CallBack callback) {
        this.callback = callback;
    }

    // 自定义Binder类
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