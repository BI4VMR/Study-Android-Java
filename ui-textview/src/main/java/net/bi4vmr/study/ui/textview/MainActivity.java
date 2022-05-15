package net.bi4vmr.study.ui.textview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // “基本应用”按钮
        Button btBase = findViewById(R.id.btBase);
        btBase.setOnClickListener(view ->
                startActivity(new Intent(this, BaseActivity.class))
        );

        // “滚动显示”按钮
        Button btMarquee = findViewById(R.id.btMarquee);
        btMarquee.setOnClickListener(view ->
                startActivity(new Intent(this, MarqueeActivity.class))
        );
    }
}