package net.bi4vmr.study.ui.extctrl.recyclerview;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.ui.extctrl.recyclerview.adapter.Adapter04;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type1VO;

import java.util.ArrayList;
import java.util.List;

public class Demo04Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_04);

        RecyclerView recyclerView = findViewById(R.id.rvContent);
        Button btAdd = findViewById(R.id.btAdd);
        Button btDelete = findViewById(R.id.btDel);
        Button btUpdate = findViewById(R.id.btUpdate);
        Button btMove = findViewById(R.id.btMove);
        Button btReload = findViewById(R.id.btReload);

        /* 初始化RecyclerView */
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 设置适配器
        Adapter04 adapter = new Adapter04(getTestDatas(), getApplicationContext());
        recyclerView.setAdapter(adapter);

        /* 初始化按钮 */
        // 新增按钮
        btAdd.setOnClickListener(v -> {
            // 插入一条记录到第3项
            Type1VO newItem = new Type1VO("这是新增加的表项");
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
            Type1VO newItem = new Type1VO("这是更新后的表项");
            adapter.updateItem(5, newItem);
        });
        // 移动按钮
        btMove.setOnClickListener(v -> adapter.moveItem(2, 5));
        // 重置按钮
        btReload.setOnClickListener(v -> adapter.reloadItem(getTestDatas()));
    }

    private List<Type1VO> getTestDatas() {
        // 制造测试数据
        List<Type1VO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new Type1VO("项目" + (i + 1)));
        }

        return datas;
    }
}