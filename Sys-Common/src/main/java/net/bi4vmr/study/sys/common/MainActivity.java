package net.bi4vmr.study.sys.common;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.sys.common.demo01.Demo01Activity;
import net.bi4vmr.study.sys.common.demo02.Demo02Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1 = findViewById(R.id.bt01);
        bt1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo01Activity.class);
            startActivity(intent);
        });

        Button bt2 = findViewById(R.id.bt02);
        bt2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo02Activity.class);
            startActivity(intent);
        });
    }
}