package net.bi4vmr.study.ui_recyclerview_refreshitem;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rv_content);
        Button bt1 = findViewById(R.id.bt_change1);
        Button bt2 = findViewById(R.id.bt_change2);

        // 制造测试数据
        List<ItemBean> datas = new ArrayList<>();
        ItemBean item1 = new ItemOneBean("项目1");
        ItemBean item2 = new ItemOneBean("项目2");
        ItemBean item3 = new ItemTwoBean("项目3");
        ItemBean item4 = new ItemTwoBean("项目4");
        datas.add(item1);
        datas.add(item2);
        datas.add(item3);
        datas.add(item4);
        for (int i = 5; i <= 20; i++) {
            datas.add(new ItemOneBean("项目" + i));
        }

        // 配置RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 数据源中第三、四项是类型2,其它项都是类型1。
        MyListAdapter adapter = new MyListAdapter(datas, this);
        recyclerView.setAdapter(adapter);

        // 改变第二项描述的按钮
        bt1.setOnClickListener(v -> {
            ItemOneBean newData = new ItemOneBean();
            newData.setComment("这是新的描述");
            adapter.updateItemData(1, newData);
        });

        // 改变第三项标题的按钮
        bt2.setOnClickListener(v -> {
            ItemTwoBean newData = new ItemTwoBean();
            newData.setTitle("这是新的标题");
            adapter.updateItemData(2, newData);
        });
    }
}