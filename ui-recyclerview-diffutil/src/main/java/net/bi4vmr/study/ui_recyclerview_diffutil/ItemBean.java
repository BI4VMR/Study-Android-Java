package net.bi4vmr.study.ui_recyclerview_diffutil;

/**
 * Name          : ItemBean
 * Author        : BI4VMR
 * Date          : 2022-04-06 10:37
 * Description   : 列表项的实体类
 */
public class ItemBean {

    private int id;
    private String title;
    private String comment;

    public ItemBean() {
    }

    public ItemBean(int id, String title) {
        this.id = id;
        this.title = title;
        this.comment = "描述文字";
    }

    public ItemBean(int id, String title, String comment) {
        this.id = id;
        this.title = title;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
