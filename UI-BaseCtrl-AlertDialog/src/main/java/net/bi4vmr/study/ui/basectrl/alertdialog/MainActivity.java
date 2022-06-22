package net.bi4vmr.study.ui.basectrl.alertdialog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.basectrl.alertdialog.demo01.Demo01Activity;
import net.bi4vmr.study.ui.basectrl.alertdialog.demo02.Demo02Activity;
import net.bi4vmr.study.ui.basectrl.alertdialog.demo03.Demo03Activity;
import net.bi4vmr.study.ui.basectrl.alertdialog.demo04.Demo04Activity;
import net.bi4vmr.study.ui.basectrl.alertdialog.demo05.Demo05Activity;

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

        // 基本应用
        Button bt2 = findViewById(R.id.bt02);
        bt2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo02Activity.class);
            startActivity(intent);
        });

        // 基本应用
        Button bt3 = findViewById(R.id.bt03);
        bt3.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo03Activity.class);
            startActivity(intent);
        });

        // 自定义布局
        Button bt4 = findViewById(R.id.bt04);
        bt4.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo04Activity.class);
            startActivity(intent);
        });

        // 自定义窗体
        Button bt5 = findViewById(R.id.bt05);
        bt5.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo05Activity.class);
            startActivity(intent);
        });
    }
}