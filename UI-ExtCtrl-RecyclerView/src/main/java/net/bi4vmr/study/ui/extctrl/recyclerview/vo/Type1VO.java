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
    private String info;

    public Type1VO() {
    }

    public Type1VO(String title) {
        this.title = title;
        info = "-";
    }

    public Type1VO(String title, String info) {
        this.title = title;
        this.info = info;
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
}
