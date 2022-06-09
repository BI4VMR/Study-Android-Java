package net.bi4vmr.study.ui.extctrl.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.ui.extctrl.recyclerview.adapter.Adapter01;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type1VO;

import java.util.ArrayList;
import java.util.List;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        // 添加数据
        List<Type1VO> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new Type1VO("项目" + (i + 1), "描述文字"));
        }

        RecyclerView recyclerView = findViewById(R.id.rvContent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Adapter01 adapter = new Adapter01(datas, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}