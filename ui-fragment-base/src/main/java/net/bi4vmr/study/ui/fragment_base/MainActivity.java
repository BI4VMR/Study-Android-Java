package net.bi4vmr.study.ui.fragment_base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButtonToggleGroup btGroup = findViewById(R.id.btgroup);
        btGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (checkedId == R.id.bt_home & isChecked) {
                transaction.replace(R.id.layout_content, HomeFragment.newInstance("1", "2"));
            } else if (checkedId == R.id.bt_fun1 & isChecked) {
                transaction.replace(R.id.layout_content, Function1Fragment.newInstance("1", "2"));
            } else if (checkedId == R.id.bt_fun2 & isChecked) {
                transaction.replace(R.id.layout_content, Function2Fragment.newInstance("1", "2"));
            } else if (checkedId == R.id.bt_about & isChecked) {
                transaction.replace(R.id.layout_content, AboutFragment.newInstance("1", "2"));
            }
            transaction.commit();
        });
    }
}