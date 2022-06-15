package net.bi4vmr.study.store.db.room.demo01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.store.db.room.R;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        StudentDB studentDB = StudentDB.getInstance(this);
        StudentDAO dao = studentDB.studentDAO();
        dao.addStudent(new Student(1, "A", 24));
    }
}