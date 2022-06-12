package net.bi4vmr.study.ui.extctrl.recyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.ui.extctrl.recyclerview.adapter.Adapter02;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type1VO;

import java.util.ArrayList;
import java.util.List;

public class Demo02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);

        // 制造测试数据
        List<Type1VO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new Type1VO("项目" + (i + 1), "描述文字"));
        }

        // 获取控件实例
        RecyclerView recyclerView = findViewById(R.id.rvContent);
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置适配器
        Adapter02 adapter = new Adapter02(datas, getApplicationContext());
        recyclerView.setAdapter(adapter);
        // 设置表项点击监听器
        adapter.setItemClickListener(position -> {
            // “表项点击”事件回调
            Toast.makeText(this, "表项" + (position + 1), Toast.LENGTH_SHORT)
                    .show();
        });
    }
}