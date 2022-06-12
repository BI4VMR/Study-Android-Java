package net.bi4vmr.study.ui.extctrl.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button bt1 = findViewById(R.id.bt01);
        bt1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo01Activity.class);
            startActivity(intent);
        });

        // 表项点击事件
        Button bt2 = findViewById(R.id.bt02);
        bt2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo02Activity.class);
            startActivity(intent);
        });

        // 多种View类型
        Button bt3 = findViewById(R.id.bt03);
        bt3.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo03Activity.class);
            startActivity(intent);
        });

        // 动态更新表项
        Button bt4 = findViewById(R.id.bt04);
        bt4.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo04Activity.class);
            startActivity(intent);
        });

        // 局部刷新
        Button bt5 = findViewById(R.id.bt05);
        bt5.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo05Activity.class);
            startActivity(intent);
        });

        // DiffUtil
        Button bt6 = findViewById(R.id.bt06);
        bt6.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo06Activity.class);
            startActivity(intent);
        });

        // 缓存与复用
        Button bt7 = findViewById(R.id.bt07);
        bt7.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo07Activity.class);
            startActivity(intent);
        });
    }
}