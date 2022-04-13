package net.bi4vmr.study.ui_recyclerview_diffutil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
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
        Button btRefresh = findViewById(R.id.bt_refresh);

        // 配置RecyclerView
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 数据源中第三、四项是类型2,其它项都是类型1。
        List<ItemBean> datas = getTestDatas();
        MyListAdapter adapter = new MyListAdapter(datas, this);
        recyclerView.setAdapter(adapter);

        btRefresh.setOnClickListener(v -> {
            Log.i("myapp", datas.toString());
            // 复制一份新的数据集
            List<ItemBean> newDatas = adapter.getCopyOfDataSource();

            // 改变部分表项以及表项的数据
            newDatas.get(2).setComment("改变表项的备注");
            newDatas.get(4).setTitle("改变表项的标题");
            newDatas.remove(6);
            Log.i("myapp", newDatas.toString());
            //
            DiffUtil.calculateDiff(new MyDiffCallback(datas, newDatas)).dispatchUpdatesTo(adapter);
            // 刷新数据集
            adapter.changeDataSource(newDatas);
        });
    }

    private List<ItemBean> getTestDatas() {
        // 制造测试数据
        List<ItemBean> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            datas.add(new ItemBean(num, "项目" + num));
        }

        return datas;
    }
}