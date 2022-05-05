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
        Button btReset = findViewById(R.id.bt_reset);

        // 配置RecyclerView
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        List<ItemBean> datas = getTestDatas();
        MyListAdapter adapter = new MyListAdapter(datas, this);
        recyclerView.setAdapter(adapter);

        // 刷新数据集按钮
        btRefresh.setOnClickListener(v -> {
            // 获取当前列表的数据源
            List<ItemBean> oldDatas = adapter.getDataSource();
            Log.i("myapp", oldDatas.toString());
            // 复制一份数据集
            List<ItemBean> newDatas = adapter.getCopyOfDataSource();

            // 改变部分表项以及表项的数据
            newDatas.get(2).setComment("改变表项的备注");
            newDatas.get(4).setTitle("改变表项的标题");
            newDatas.remove(6);
            newDatas.add(8, new ItemBean(getID(), "新增表项", "-"));
            Log.i("myapp", newDatas.toString());

            // 更新数据源
            adapter.updateDataSource(newDatas);
            // 对比新旧列表的差异
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(oldDatas, newDatas));
            // 将比对结果应用到适配器，刷新控件。
            diffResult.dispatchUpdatesTo(adapter);
        });

        // 重置按钮
        btReset.setOnClickListener(v -> adapter.reloadItem(getTestDatas()));
    }

    /**
     * Name        获取测试数据
     * Author      BI4VMR
     * Date        2022-04-26 00:56
     * Description 获取测试数据
     *
     * @return 测试数据源
     */
    private List<ItemBean> getTestDatas() {
        // 制造测试数据
        List<ItemBean> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            datas.add(new ItemBean(num, "项目" + num));
        }

        return datas;
    }

    /**
     * Name        生成随机ID
     * Author      BI4VMR
     * Date        2022-04-26 01:02
     * Description 获取一个ID，取值范围：(20,100)
     *
     * @return 整形数值，即生成的ID。
     */
    private int getID() {
        int num;
        do {
            num = (int) (Math.random() * 100);
        } while (num > 20);

        return num;
    }
}