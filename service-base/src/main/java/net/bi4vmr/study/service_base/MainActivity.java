package net.bi4vmr.study.service_base;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 文本框
        TextView textView = findViewById(R.id.tv_text);
        textView.setText("等待操作...");
        // 启动按钮
        Button btStart = findViewById(R.id.bt_start);
        btStart.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            i.putExtra("LINK", "https://dl.test.com/file");
            startService(i);
            textView.setText("服务已启动");
        });

        // 停止按钮
        Button btStop = findViewById(R.id.bt_stop);
        btStop.setOnClickListener(v -> {
            Intent i = new Intent(this, DownloadService.class);
            boolean isSuccess = stopService(i);
            if (isSuccess) {
                textView.setText("服务已停止");
            }
        });
    }
}