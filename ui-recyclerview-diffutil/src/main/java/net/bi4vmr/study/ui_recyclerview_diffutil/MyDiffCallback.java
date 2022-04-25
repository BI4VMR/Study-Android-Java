package net.bi4vmr.study.ui_recyclerview_diffutil;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * Name        MyDiffCallback
 * Version     1.0
 * Author      BI4VMR
 * Date        2022-04-11 21:17
 * Description 自定义的"DiffUtil.Callback"子类，用于设置Item的比较规则。
 */
public class MyDiffCallback extends DiffUtil.Callback {

    private final List<ItemBean> oldList;
    private final List<ItemBean> newList;

    public MyDiffCallback(List<ItemBean> oldList, List<ItemBean> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    /**
     * Name        : getOldListSize()
     * Author      : BI4VMR
     * Date        : 2022-04-25 11:08
     * Description : 系统回调方法，设置旧数据源的大小。
     * @return 旧数据源的大小
     */
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    /**
     * Name        : getNewListSize()
     * Author      : BI4VMR
     * Date        : 2022-04-25 11:08
     * Description : 系统回调方法，设置新数据源的大小。
     * @return 新数据源的大小
     */
    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ItemBean oldItem = oldList.get(oldItemPosition);
        ItemBean newItem = newList.get(newItemPosition);
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ItemBean oldItem = oldList.get(oldItemPosition);
        ItemBean newItem = newList.get(newItemPosition);

        boolean isTitleSame = oldItem.getTitle().equals(newItem.getTitle());
        boolean isCommentSame = oldItem.getComment().equals(newItem.getComment());
        return isTitleSame & isCommentSame;
    }
}
