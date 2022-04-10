package net.bi4vmr.study.ui_recyclerview_refreshlist;

/**
 * Name          : ItemBean
 * Author        : BI4VMR
 * Date          : 2022-04-06 10:37
 * Description   : 列表项的实体类
 */
public class ItemBean {

    private String title;
    private String comment;

    public ItemBean() {
    }

    public ItemBean(String title) {
        this.title = title;
        this.comment = "描述";
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
