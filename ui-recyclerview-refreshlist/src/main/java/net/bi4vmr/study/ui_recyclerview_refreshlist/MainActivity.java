package net.bi4vmr.study.ui_recyclerview_refreshlist;

import android.os.Bundle;
import android.widget.Button;

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
        Button btAdd = findViewById(R.id.bt_add);
        Button btDelete = findViewById(R.id.bt_del);
        Button btUpdate = findViewById(R.id.bt_update);
        Button btMove = findViewById(R.id.bt_move);
        Button btSwap = findViewById(R.id.bt_swap);
        Button btReload = findViewById(R.id.bt_reload);

        // 配置RecyclerView
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 数据源中第三、四项是类型2,其它项都是类型1。
        MyListAdapter adapter = new MyListAdapter(getTestDatas(), this);
        recyclerView.setAdapter(adapter);

        // 新增按钮
        btAdd.setOnClickListener(v -> {
            // 插入一条记录到第3项
            ItemBean newItem = new ItemBean("这是新增加的表项");
            adapter.addItem(2, newItem);
        });
        // 删除按钮
        btDelete.setOnClickListener(v -> {
            // 删除第4项
            adapter.removeItem(4);
        });
        // 更新按钮
        btUpdate.setOnClickListener(v -> {
            // 更新第6项
            ItemBean newItem = new ItemBean("这是更新后的表项");
            adapter.updateItem(5, newItem);
        });
        // 移动按钮
        btMove.setOnClickListener(v -> adapter.moveItem(2, 5));
        // 交换按钮
        btSwap.setOnClickListener(v -> adapter.swapItems(1, 4));
        // 重置按钮
        btReload.setOnClickListener(v -> adapter.reloadItem(getTestDatas()));
    }

    private List<ItemBean> getTestDatas() {
        // 制造测试数据
        List<ItemBean> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new ItemBean("项目" + (i + 1)));
        }

        return datas;
    }
}