package net.bi4vmr.study.fragment_dynamic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Function1Fragment f1;
    Function2Fragment f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建Fragment实例
        f1 = Function1Fragment.newInstance();
        f2 = Function2Fragment.newInstance();

        //获取FragmentManager实例
        FragmentManager manager = getSupportFragmentManager();

        //添加按钮
        Button btAdd1 = findViewById(R.id.bt_add1);
        btAdd1.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //添加Fragment
            transaction.add(R.id.layout_container, f1);
            //提交事务
            transaction.commit();
        });

        //替换按钮
        Button btReplace1 = findViewById(R.id.bt_replace1);
        btReplace1.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //替换Fragment
            transaction.replace(R.id.layout_container, f1);
            //提交事务
            transaction.commit();
        });

        //移除按钮
        Button btRemove1 = findViewById(R.id.bt_remove1);
        btRemove1.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //移除Fragment
            transaction.remove(f1);
            //提交事务
            transaction.commit();
        });

        //显示按钮
        Button btShow1 = findViewById(R.id.bt_show1);
        btShow1.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //隐藏Fragment
            transaction.show(f1);
            //提交事务
            transaction.commit();
        });

        //隐藏按钮
        Button btHide1 = findViewById(R.id.bt_hide1);
        btHide1.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //隐藏Fragment
            transaction.hide(f1);
            //提交事务
            transaction.commit();
        });

        //添加按钮
        Button btAdd2 = findViewById(R.id.bt_add2);
        btAdd2.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //添加Fragment
            transaction.add(R.id.layout_container, f2);
            //提交事务
            transaction.commit();
        });

        //替换按钮
        Button btReplace2 = findViewById(R.id.bt_replace2);
        btReplace2.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //替换Fragment
            transaction.replace(R.id.layout_container, f2);
            //提交事务
            transaction.commit();
        });

        //移除按钮
        Button btRemove2 = findViewById(R.id.bt_remove2);
        btRemove2.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //移除Fragment
            transaction.remove(f2);
            //提交事务
            transaction.commit();
        });

        //显示按钮
        Button btShow2 = findViewById(R.id.bt_show2);
        btShow2.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //隐藏Fragment
            transaction.show(f2);
            //提交事务
            transaction.commit();
        });

        //隐藏按钮
        Button btHide2 = findViewById(R.id.bt_hide2);
        btHide2.setOnClickListener(v -> {
            //获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            //隐藏Fragment
            transaction.hide(f2);
            //提交事务
            transaction.commit();
        });
    }
}