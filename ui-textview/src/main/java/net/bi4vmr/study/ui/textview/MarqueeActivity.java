package net.bi4vmr.study.ui.textview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MarqueeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);

        // 构造测试文本
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append(i);
        }
        String text = builder.toString();

        // 绑定控件
        TextView tvMarquee = findViewById(R.id.tvMarquee);
        // 设置文本
        tvMarquee.setText(text);
        // 设置选中状态为"true"
        tvMarquee.setSelected(true);
    }
}