package net.bi4vmr.study.ui.extctrl.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.ui.extctrl.recyclerview.adapter.Adapter07;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type1VO;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type2VO;

import java.util.ArrayList;
import java.util.List;

public class Demo07Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_07);

        // 配置RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvContent);
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 数据源中第三、四项是类型II,其它项都是类型I。
        Adapter07 adapter = new Adapter07(getTestDatas(), this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Name        获取测试数据
     * Author      BI4VMR
     * Date        2022-04-26 00:56
     * Description 获取测试数据
     *
     * @return 测试数据源
     */
    private List<Object> getTestDatas() {
        // 制造测试数据
        List<Object> datas = new ArrayList<>();
        Type1VO item1 = new Type1VO("项目1");
        Type1VO item2 = new Type1VO("项目2");
        Type2VO item3 = new Type2VO("项目3");
        Type2VO item4 = new Type2VO("项目4");
        datas.add(item1);
        datas.add(item2);
        datas.add(item3);
        datas.add(item4);
        for (int i = 5; i <= 20; i++) {
            datas.add(new Type1VO("项目" + i));
        }

        return datas;
    }
}