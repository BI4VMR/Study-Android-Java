package net.bi4vmr.study.ui.extctrl.recyclerview;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import net.bi4vmr.study.ui.extctrl.recyclerview.vo.ItemVO;

import java.util.List;

/**
 * Name        MyDiffCallback
 * Version     1.0
 * Author      BI4VMR
 * Date        2022-04-11 21:17
 * Description 自定义的"DiffUtil.Callback"子类，用于设置Item的比较规则。
 */
public class MyDiffCallback extends DiffUtil.Callback {

    private final List<ItemVO> oldList;
    private final List<ItemVO> newList;

    public MyDiffCallback(List<ItemVO> oldList, List<ItemVO> newList) {
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
     * Name        : areItemsTheSame()
     * Description : 系统回调方法，判断参数所指定的两个位置对应Item是否相同。
     *
     * @param oldItemPosition 旧的Item索引
     * @param newItemPosition 新的Item索引
     * @return 布尔值，值为"true"时表示两个Item相同，但数据可能有变化，随后会调用"areContentsTheSame()"方法判
     * 断Item中的数据是否需要更新。值为"false"时，表示两个Item不同。
     * 如果Item有ID或类似的属性，可以比较ID是否相同。
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ItemVO oldItem = oldList.get(oldItemPosition);
        ItemVO newItem = newList.get(newItemPosition);
        // 若Item具有相同的ID，则认为它们是相同的。
        return oldItem.getId() == newItem.getId();
    }

    /**
     * Name        : areContentsTheSame()
     * Description : 系统回调方法，判断参数所指定的两个位置对应Item内容是否相同。
     * 仅当"areItemsTheSame()"返回"true"时才会调用本方法。
     *
     * @param oldItemPosition 旧的Item索引
     * @param newItemPosition 新的Item索引
     * @return 布尔值，值为"true"时表示两个Item内容相同，值为"false"表示两个Item内容不同。
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ItemVO oldItem = oldList.get(oldItemPosition);
        ItemVO newItem = newList.get(newItemPosition);

        boolean isTitleSame = oldItem.getTitle().equals(newItem.getTitle());
        boolean isInfoSame = oldItem.getInfo().equals(newItem.getInfo());
        return isTitleSame & isInfoSame;
    }

    /**
     * Name        : getChangePayload
     * Description : 计算发生变化的属性，使得适配器执行局部刷新。
     *
     * @param oldItemPosition 旧的Item索引
     * @param newItemPosition 新的Item索引
     * @return 任意类型的对象，会传递给适配器"onBindViewHolder()"方法的"payloads"参数。
     * 自定义Callback不覆盖此方法时，系统默认返回"null"，此时将刷新整个列表项。
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        ItemVO oldItem = oldList.get(oldItemPosition);
        ItemVO newItem = newList.get(newItemPosition);

        boolean isTitleSame = oldItem.getTitle().equals(newItem.getTitle());
        boolean isInfoSame = oldItem.getInfo().equals(newItem.getInfo());

        // 根据局部刷新逻辑传递发生变化的部分
        ItemVO payload = new ItemVO();
        if (!isTitleSame) {
            payload.setTitle(newItem.getTitle());
        }
        if (!isInfoSame) {
            payload.setInfo(newItem.getInfo());
        }

        return payload;
    }
}
