package net.bi4vmr.study.ui.basectrl.textview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        // 绑定控件
        TextView tvBase = findViewById(R.id.tvBase);
        // 设置文本内容
        tvBase.setText("这是一个文本框");
    }
}