package net.bi4vmr.study.ui.basectrl.button;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        // 绑定控件
        Button btBase = findViewById(R.id.btBase);
        // 设置点击监听器
        btBase.setOnClickListener(v -> {
            // 用户点击按钮时触发此回调方法
            v.setBackgroundColor(Color.parseColor("#00FFFF"));
        });
    }
}