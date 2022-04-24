package net.bi4vmr.study.service_foreground;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btStart = findViewById(R.id.bt_start);
        Button btStop = findViewById(R.id.bt_stop);
        intent = new Intent(this, ForegroundService.class);

        // 启动服务按钮
        btStart.setOnClickListener(v -> startService(intent));
        // 停止服务按钮
        btStop.setOnClickListener(v -> stopService(intent));
    }
}