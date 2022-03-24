package net.bi4vmr.study.broadcast_system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 接收到广播时，需要执行的操作。
        Log.i("myapp", "Time Tick.1");
    }
}