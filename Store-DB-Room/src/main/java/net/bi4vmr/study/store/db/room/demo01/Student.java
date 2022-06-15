package net.bi4vmr.study.store.db.room.demo01;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Name        : Student
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-15 22:29
 * Description : TODO 添加描述
 */
@Entity
public class Student {

    @PrimaryKey
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
