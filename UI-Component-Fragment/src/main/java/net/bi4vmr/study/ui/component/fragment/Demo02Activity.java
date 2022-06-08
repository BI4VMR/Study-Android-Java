package net.bi4vmr.study.ui.component.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Demo02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);

        // 创建Fragment实例
        TestFragment fragment = TestFragment.newInstance("初始参数");
        // 获取FragmentManager实例
        FragmentManager manager = getSupportFragmentManager();
        // 获取Fragment事务实例
        FragmentTransaction transaction = manager.beginTransaction();
        // 添加Fragment
        transaction.add(R.id.container, fragment);
        // 提交事务
        transaction.commit();
    }
}