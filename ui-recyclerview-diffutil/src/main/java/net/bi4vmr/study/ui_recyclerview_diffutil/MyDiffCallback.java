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
     * Description : 系统回调方法，设置旧数据源的大小。
     *
     * @return 旧数据源的长度
     */
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    /**
     * Name        : getNewListSize()
     * Description : 系统回调方法，设置新数据源的大小。
     *
     * @return 新数据源的长度
     */
    @Override
    public int getNewListSize() {
        return newList.size();
    }

    /**
     * Name        areItemsTheSame()
     * Description 系统回调方法，判断参数所指定的两个位置对应Item是否相同。
     *
     * @param oldItemPosition 旧的Item索引
     * @param newItemPosition 新的Item索引
     * @return 布尔值，值为"true"时表示两个Item相同，但数据可能有变化，随后会调用"areContentsTheSame()"方法判
     *         断Item中的数据是否需要更新。值为"false"时，表示两个Item不同。
     *         如果Item有ID或类似的属性，可以比较ID是否相同。
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ItemBean oldItem = oldList.get(oldItemPosition);
        ItemBean newItem = newList.get(newItemPosition);
        return oldItem.getId() == newItem.getId();
    }

    /**
     * Name        areContentsTheSame()
     * Description 系统回调方法，判断参数所指定的两个位置对应Item内容是否相同。
     * 仅当"areItemsTheSame()"返回"true"时才会调用本方法。
     *
     * @param oldItemPosition 旧的Item索引
     * @param newItemPosition 新的Item索引
     * @return 布尔值，值为"true"时表示两个Item内容相同，值为"false"表示两个Item内容不同。
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ItemBean oldItem = oldList.get(oldItemPosition);
        ItemBean newItem = newList.get(newItemPosition);

        boolean isTitleSame = oldItem.getTitle().equals(newItem.getTitle());
        boolean isCommentSame = oldItem.getComment().equals(newItem.getComment());
        return isTitleSame & isCommentSame;
    }
}
