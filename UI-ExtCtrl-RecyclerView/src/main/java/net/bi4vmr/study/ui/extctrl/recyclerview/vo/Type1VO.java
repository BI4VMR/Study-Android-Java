package net.bi4vmr.study.ui.extctrl.recyclerview.vo;

/**
 * Name        : Type1VO
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-09 23:57
 * Description : 列表项的实体类（类型I）
 */
public class Type1VO {

    private String title;
    private String comment;

    public Type1VO(String title, String comment) {
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
