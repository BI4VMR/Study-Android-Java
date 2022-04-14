package net.bi4vmr.study.datastorage.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_Text = findViewById(R.id.tv_text);
        Button bt_Write = findViewById(R.id.bt_write);
        Button bt_Read = findViewById(R.id.bt_read);

        //写入按钮事件
        bt_Write.setOnClickListener(v -> {
            // 获取SharedPreferences实例
            SharedPreferences sp = getSharedPreferences("test", Context.MODE_PRIVATE);
            // 获取Editor实例
            SharedPreferences.Editor editor = sp.edit();
            // 存入数据
            editor.putInt("Data_Int", 100);
            editor.putBoolean("Data_Boolean", true);
            editor.putString("Data_String", "测试文字");
            // 提交变更
            editor.apply();
        });

        // 读取按钮事件
        bt_Read.setOnClickListener(v -> {
            // 获取SharedPreferences实例
            SharedPreferences sp = getSharedPreferences("MainActivity2", Context.MODE_PRIVATE);
            // 读取数值
            int i = sp.getInt("Data_Int", 0);
            boolean b = sp.getBoolean("Data_Boolean", false);
            String s = sp.getString("Data_String", "Empty.");
            // 设置文本
            tv_Text.setText("整数：" + i + "\n浮点数：" + b + "\n字符串：" + s);
        });
    }
}