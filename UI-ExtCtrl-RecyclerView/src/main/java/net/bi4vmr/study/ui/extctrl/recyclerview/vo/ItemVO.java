package net.bi4vmr.study.ui.extctrl.recyclerview.vo;

import androidx.annotation.NonNull;

/**
 * Name          : ItemBean
 * Author        : BI4VMR
 * Date          : 2022-04-06 10:37
 * Description   : 列表项的实体类。
 */
public class ItemVO {

    private int id;
    private String title;
    private String info;

    public ItemVO() {
    }

    public ItemVO(int id, String title) {
        this.id = id;
        this.title = title;
        this.info = "-";
    }

    public ItemVO(int id, String title, String info) {
        this.id = id;
        this.title = title;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @NonNull
    @Override
    public String toString() {
        return "ItemVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
