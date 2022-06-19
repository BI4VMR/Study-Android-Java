package net.bi4vmr.study.ui.extctrl.tablayout.demo01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import net.bi4vmr.study.ui.extctrl.tablayout.R;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        TabLayout tabLayout = findViewById(R.id.layoutTab);

        // 创建Tab
        TabLayout.Tab tab1 = tabLayout.newTab()
                .setText("页面一");
        TabLayout.Tab tab2 = tabLayout.newTab()
                .setText("页面二");
        TabLayout.Tab tab3 = tabLayout.newTab()
                .setText("页面三");

        // 将Tab按顺序添加至TabLayout中
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
    }
}