package net.bi4vmr.study.ui.basectrl.toast;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);

        // 显示Toast按钮
        Button btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(v -> {
            // 创建Toast实例
            Toast toast = Toast.makeText(getApplicationContext(), "My Toast", Toast.LENGTH_LONG);
            // 设置Toast相对于屏幕边缘的位置与偏移量
            toast.setGravity(Gravity.TOP, 0, 275);
            // 获取Toast的布局实例
            LinearLayout layout = (LinearLayout) toast.getView();

            // 创建ImageView实例
            ImageView iv = new ImageView(getApplicationContext());
            iv.setImageResource(R.mipmap.funny_256);

            // 将ImageView添加到Toast布局中
            layout.addView(iv);
            // 显示Toast
            toast.show();
        });
    }
}