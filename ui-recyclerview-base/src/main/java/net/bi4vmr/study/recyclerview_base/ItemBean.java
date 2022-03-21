package net.bi4vmr.study.recyclerview_base;

/**
 * 列表项的实体类
 */
public class ItemBean {

    private String title;
    private String comment;

    public ItemBean(String title, String comment) {
        this.title = title;
        this.comment = comment;
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
