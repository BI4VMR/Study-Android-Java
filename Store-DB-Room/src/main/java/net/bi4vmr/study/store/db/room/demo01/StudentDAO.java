package net.bi4vmr.study.store.db.room.demo01;

import androidx.room.Dao;
import androidx.room.Insert;

/**
 * Name        : StudentDAO
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-15 22:37
 * Description : TODO 添加描述
 */
@Dao
public interface StudentDAO {

    @Insert
    void addStudent(Student student);
}
