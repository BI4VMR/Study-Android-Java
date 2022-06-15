package net.bi4vmr.study.store.db.room.demo01;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Name        : StudentDB
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-15 22:40
 * Description : TODO 添加描述
 */
@Database(entities = Student.class, version = 1, exportSchema = false)
public abstract class StudentDB extends RoomDatabase {

    private static StudentDB instance;

    public static synchronized StudentDB getInstance(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(), StudentDB.class, "aaa")
                            // Room默认在子线程执行SQL语句，此语句可以切换至主线程操作，仅适用于调试。
                            .allowMainThreadQueries()
                            .build();
        }
        return instance;
    }

    public abstract StudentDAO studentDAO();
}
