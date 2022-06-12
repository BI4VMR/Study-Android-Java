package net.bi4vmr.study.ui.extctrl.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.ui.extctrl.recyclerview.adapter.Adapter03;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type1VO;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type2VO;

import java.util.ArrayList;
import java.util.List;

public class Demo03Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_03);

        // 制造测试数据
        List<Object> datas = new ArrayList<>();
        datas.add(new Type1VO("项目一", "这是类型I"));
        datas.add(new Type1VO("项目二", "这是类型I"));
        datas.add(new Type2VO("这是类型II"));
        datas.add(new Type1VO("项目三", "这是类型I"));
        datas.add(new Type2VO("这是类型II"));

        // 获取控件实例
        RecyclerView recyclerView = findViewById(R.id.rvContent);
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置适配器
        Adapter03 adapter = new Adapter03(datas, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}