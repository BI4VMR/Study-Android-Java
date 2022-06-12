package net.bi4vmr.study.ui.extctrl.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.ui.extctrl.recyclerview.adapter.Adapter06;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.ItemVO;

import java.util.ArrayList;
import java.util.List;

public class Demo06Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_06);

        RecyclerView recyclerView = findViewById(R.id.rvContent);
        Button btRefresh = findViewById(R.id.btRefresh);
        Button btReset = findViewById(R.id.btReset);

        // 配置RecyclerView
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        List<ItemVO> datas = getTestDatas();
        Adapter06 adapter = new Adapter06(datas, this);
        recyclerView.setAdapter(adapter);

        // 刷新数据集按钮
        btRefresh.setOnClickListener(v -> {
            // 获取当前列表的数据源
            List<ItemVO> oldDatas = adapter.getDataSource();
            Log.i("myapp", "old:" + oldDatas);

            // 复制一份数据集
            List<ItemVO> newDatas = adapter.getCopyOfDataSource();
            // 模拟数据变更
            newDatas.get(2).setInfo("这是新的备注");
            newDatas.get(4).setTitle("这是新的标题");
            newDatas.remove(1);
            newDatas.remove(5);
            newDatas.add(8, new ItemVO(generateID(), "新增表项"));
            Log.i("myapp", "new:" + newDatas);

            // 在新线程中进行耗时操作
            new Thread(() -> {
                // 对比新旧列表的差异
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(oldDatas, newDatas), false);
                // 更新数据源
                adapter.updateDataSource(newDatas);
                // 切换至主线程，更新视图。
                runOnUiThread(() -> diffResult.dispatchUpdatesTo(adapter));
            }).start();
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
    private List<ItemVO> getTestDatas() {
        // 制造测试数据
        List<ItemVO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            datas.add(new ItemVO(num, "项目" + num));
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
    private int generateID() {
        int num;
        do {
            num = (int) (Math.random() * 100);
        } while (num > 20);

        return num;
    }
}