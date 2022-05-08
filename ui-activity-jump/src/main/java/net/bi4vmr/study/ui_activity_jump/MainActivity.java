package net.bi4vmr.study.ui_activity_jump;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 实例化按钮控件
        Button btGoToSec = findViewById(R.id.bt_toSecActivity);
        // 设置按钮控件的点击事件
        btGoToSec.setOnClickListener(v -> {
            // 创建Intent对象
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            // 启动SecondActivity
            startActivity(intent);
        });

        // 实例化按钮控件
        Button btGoToWeb = findViewById(R.id.bt_toWebActivity);
        // 设置按钮控件的点击事件
        btGoToWeb.setOnClickListener(v -> {
            // 构造URI，指定目标URL地址。
            Uri uri = Uri.parse("https://cn.bing.com/");
            // 创建Intent对象
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            // 启动默认浏览器的Activity
            startActivity(intent);
        });
    }
}