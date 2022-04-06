package net.bi4vmr.study.ui_recyclerview_viewcache;

/**
 * Name          : ItemTwoBean
 * Author        : BI4VMR
 * Date          : 2022-04-06 10:37
 * Description   : 列表项的实体类（类型二）
 */
public class ItemTwoBean implements ItemBean {

    public static final int VIEWTYPE = 2;

    private String title;
    private String comment;

    public ItemTwoBean() {
    }

    public ItemTwoBean(String title) {
        this.title = title;
        this.comment = "类型二";
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

    @Override
    public int getViewType() {
        return VIEWTYPE;
    }
}
