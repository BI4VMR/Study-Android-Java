package net.bi4vmr.study.ui_recyclerview_viewcache;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        // 数据源中第三、四项是类型2,其它项都是类型1。
        MyListAdapter adapter = new MyListAdapter(datas, this);
        recyclerView.setAdapter(adapter);
    }
}