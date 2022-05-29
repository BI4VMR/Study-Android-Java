package net.bi4vmr.study.ui.layout.constraint;

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

        // 按照比例约束控件的位置
        Button bt2 = findViewById(R.id.bt02);
        bt2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo02Activity.class);
            startActivity(intent);
        });

        // 按照比例约束控件的宽高
        Button bt3 = findViewById(R.id.bt03);
        bt3.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo03Activity.class);
            startActivity(intent);
        });

        // 链式约束与权重属性
        Button bt4 = findViewById(R.id.bt04);
        bt4.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo04Activity.class);
            startActivity(intent);
        });

        // 角度约束
        Button bt5 = findViewById(R.id.bt05);
        bt5.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo05Activity.class);
            startActivity(intent);
        });

        // 辅助线
        Button bt6 = findViewById(R.id.bt06);
        bt6.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo06Activity.class);
            startActivity(intent);
        });

        // 屏障
        Button bt7 = findViewById(R.id.bt07);
        bt7.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo07Activity.class);
            startActivity(intent);
        });
    }
}