package net.bi4vmr.study.service_aidl_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;

import net.bi4vmr.study.aidl.IMyAIDLInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAIDLInterface aidlInterface;
    private boolean isServiceConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btGetMSG = findViewById(R.id.bt_getmsg);
        Button btConnect = findViewById(R.id.bt_connect);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                aidlInterface = IMyAIDLInterface.Stub.asInterface(service);
                // 连接标记位置为"true"
                isServiceConnected = true;
                Log.i("myapp", "连接已就绪");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                // 连接标记位置为"false"
                isServiceConnected = false;
            }
        };

        btConnect.setOnClickListener(v -> {
            Intent i = new Intent();
//        i.setAction("net.bi4vmr.study.aidl.MyService");
            i.setPackage("net.bi4vmr.study.aidl");
            i.setClassName("net.bi4vmr.study.aidl", "net.bi4vmr.study.aidl.MyService");
            bindService(i, connection, BIND_AUTO_CREATE);
        });

        btGetMSG.setOnClickListener(v -> {
            // 根据连接状态标志位确定是否能够访问接口
            if (isServiceConnected) {
                try {
                    Log.i("myapp", aidlInterface.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                Log.i("myapp", "连接未就绪");
            }
        });
    }
}