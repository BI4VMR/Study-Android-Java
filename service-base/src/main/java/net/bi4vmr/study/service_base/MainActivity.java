package net.bi4vmr.study.service_base;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 文本框
        TextView textView=findViewById(R.id.tv_text);
        // 按钮
        Button bt=findViewById(R.id.bt_chtext);
        bt.setOnClickListener(v -> {
            Intent i =new Intent(this,MyService.class);
            startService(i);

        });
    }
}