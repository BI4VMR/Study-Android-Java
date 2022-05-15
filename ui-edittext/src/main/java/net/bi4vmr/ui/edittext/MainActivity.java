package net.bi4vmr.ui.edittext;

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
    }
}