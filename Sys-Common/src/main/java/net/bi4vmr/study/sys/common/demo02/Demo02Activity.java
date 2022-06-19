package net.bi4vmr.study.sys.common.demo02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.sys.common.R;

import java.util.ArrayList;
import java.util.List;

public class Demo02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);

        Button btGoTo = findViewById(R.id.btGoToDest);

        // 创建Book对象
        List<String> type = new ArrayList<>();
        type.add("Android");
        type.add("Java");
        Book book = new Book(1, "第一行代码", type);

        // 点击按钮时触发操作
        btGoTo.setOnClickListener(v -> {
            // 将Book对象打包并传递给Demo02DstActivity
            Intent intent = new Intent(this, Demo02DstActivity.class);
            intent.putExtra("BOOK_INFO", book);
            startActivity(intent);
        });
    }
}