package net.bi4vmr.study.ui_recyclerview_viewcache;

/**
 * Name          : ItemBean
 * Author        : BI4VMR
 * Date          : 2022-04-06 10:42
 * Description   : 列表项的实体类公共接口
 */
public interface ItemBean {

    /**
     * Name        : 获取当前Item的ViewType
     * Author      : BI4VMR
     * Date        : 2022-4-6 11:17
     * Description : 获取当前Item的ViewType
     * @return 整形，表示当前Item的ViewType。
     */
    int getViewType();
}
