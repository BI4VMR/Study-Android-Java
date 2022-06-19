package net.bi4vmr.study.sys.common.demo02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.sys.common.R;

public class Demo02DstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02_dst);

        TextView tvInfo = findViewById(R.id.tvInfo);

        // 获取Intent中的数据
        Intent intent = getIntent();
        if (intent != null) {
            Book book = intent.getParcelableExtra("BOOK_INFO");
            if (book != null) {
                // 将接收到的Book信息设置在TextView中
                tvInfo.setText(book.toString());
            }
        }
    }
}