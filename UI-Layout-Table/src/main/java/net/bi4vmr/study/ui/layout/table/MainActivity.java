package net.bi4vmr.study.ui.layout.table;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.layout.table.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button bt1 = findViewById(R.id.bt01);
        bt1.setOnClickListener(v -> {
            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
        });
    }
}