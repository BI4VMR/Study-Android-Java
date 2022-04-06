package net.bi4vmr.study.ui_recyclerview_refreshitem;

/**
 * Name          : ItemTwoBean
 * Author        : 詹屹罡
 * Date          : 2022-04-06 10:37
 * Description   : 列表项的实体类（类型一）
 */
public class ItemOneBean implements ItemBean {

    public static final int VIEWTYPE = 1;

    private String title;
    private String comment;

    public ItemOneBean() {
    }

    public ItemOneBean(String title) {
        this.title = title;
        this.comment = "类型一";
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
