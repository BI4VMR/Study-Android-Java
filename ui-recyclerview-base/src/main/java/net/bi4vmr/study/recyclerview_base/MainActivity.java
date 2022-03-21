package net.bi4vmr.study.recyclerview_base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加数据
        List<ItemBean> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new ItemBean("项目" + (i + 1), "描述文字"));
        }

        RecyclerView recyclerView = findViewById(R.id.rv_content);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter adapter = new MyAdapter(datas, this);
        recyclerView.setAdapter(adapter);
//        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }
}