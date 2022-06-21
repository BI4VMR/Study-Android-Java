package net.bi4vmr.study.ui.basectrl.alertdialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Demo05Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_05);

        // 创建对话框构造器并进行配置
        AlertDialog dialog = new AlertDialog.Builder(this)
                // 设置标题
                .setTitle("警告对话框")
                // 设置图标
                .setIcon(R.drawable.ic_funny_256)
                // 设置内容
                .setMessage("此处为消息内容")
                // 创建对话框
                .create();

        Window window=dialog.getWindow();
        WindowManager.LayoutParams layoutParam=window.getAttributes();
        layoutParam.width=100;
        layoutParam.gravity= Gravity.TOP;
        window.setAttributes(layoutParam);


        // 显示弹窗按钮
        Button btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(v -> {
            // 显示对话框
            dialog.show();
        });
    }
}