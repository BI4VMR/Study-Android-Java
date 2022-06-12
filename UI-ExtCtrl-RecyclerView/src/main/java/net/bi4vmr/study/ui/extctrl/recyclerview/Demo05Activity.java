package net.bi4vmr.study.ui.extctrl.recyclerview;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.ui.extctrl.recyclerview.adapter.Adapter05;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type1VO;

import java.util.ArrayList;
import java.util.List;

public class Demo05Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_05);

        // 制造测试数据
        List<Type1VO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new Type1VO("项目" + (i + 1)));
        }

        // 获取控件实例
        RecyclerView recyclerView = findViewById(R.id.rvContent);
        Button bt1 = findViewById(R.id.btChange1);
        Button bt2 = findViewById(R.id.btChange2);

        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 设置适配器
        Adapter05 adapter = new Adapter05(datas, getApplicationContext());
        recyclerView.setAdapter(adapter);

        // 改变第二项描述的按钮
        bt1.setOnClickListener(v -> {
            Type1VO newData = new Type1VO();
            newData.setInfo("这是新的描述");
            adapter.updateItem(1, newData);
        });

        // 改变第三项标题的按钮
        bt2.setOnClickListener(v -> {
            Type1VO newData = new Type1VO();
            newData.setTitle("这是新的标题");
            adapter.updateItem(2, newData);
        });
    }
}