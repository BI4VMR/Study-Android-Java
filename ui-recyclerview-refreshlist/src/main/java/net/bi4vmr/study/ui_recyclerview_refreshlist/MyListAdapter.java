package net.bi4vmr.study.ui_recyclerview_refreshlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    // 数据源
    List<ItemBean> dataSource;
    // 上下文环境
    Context mContext;

    /**
     * Name        有参构造方法
     * Author      BI4VMR
     * Date        2022-4-9 17:07
     * Description 适配器构造方法，用于初始化适配器实例。
     *
     * @param dataSource 数据源，类型是含有ItemBean的List，传入"null"时自动初始化空列表。
     * @param context    上下文环境
     */
    public MyListAdapter(List<ItemBean> dataSource, Context context) {
        // 外部传入的数据源为空时，创建空的列表，以免发生异常。
        if (dataSource == null) {
            dataSource = new ArrayList<>();
        }
        this.dataSource = dataSource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("myapp", "onCreateViewHolder() ViewType:" + viewType);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("myapp", "onBindViewHolder() Position:" + position);
        //获取当前项的数据
        ItemBean item = dataSource.get(position);
        //将数据设置到当前项的控件中
        holder.tv_title.setText(item.getTitle());
        holder.tv_comment.setText(item.getComment());
        holder.iv_icon.setImageResource(R.drawable.ic_launcher_foreground);
        holder.iv_icon.setBackgroundResource(R.drawable.ic_launcher_background);
        holder.itemView.setOnClickListener(v ->
                Log.d("myapp", "第" + (position + 1) + "项被点击了"));
    }

    /**
     * 获取当前列表中Item的数量
     *
     * @return Item的数量。
     */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * Name        更新表项
     * Author      BI4VMR
     * Date        2022-4-10 11:21
     * Description 更新指定的表项，不影响列表中的其它表项。
     *
     * @param position 待更新的表项在列表中的位置
     * @param item     新的表项实例
     */
    public void updateItem(int position, ItemBean item) {
        // 更新数据源
        dataSource.remove(position);
        dataSource.add(position, item);
        // 通知RecyclerView指定表项被更改，刷新控件显示。
        notifyItemChanged(position);
    }

    /**
     * Name        插入表项
     * Author      BI4VMR
     * Date        2022-4-10 11:27
     * Description 将新的表项插入到指定位置，若该位置已存在表项，则将其本身以及后继表项都后移一位。
     *
     * @param position 待插入的位置
     * @param item     新的表项实例
     */
    public void addItem(int position, ItemBean item) {
        // 更新数据源
        dataSource.add(position, item);
        // 通知RecyclerView新的表项被插入，刷新控件显示。
        notifyItemInserted(position);
        /*
         * 通知RecyclerView刷新插入位置之后的表项，使得"onBindViewHolder()"方法重新执行。
         * 如果忘记执行此方法，插入位置之后的表项Position属性不会被更新，通过Position访问数据集可能会出错。
         */
        int itemCount = dataSource.size() - (position + 1);
        if (itemCount != 0) {
            notifyItemRangeChanged(position + 1, itemCount);
        }
    }

    /**
     * Name        移除表项
     * Author      BI4VMR
     * Date        2022-4-10 22:34
     * Description 移除指定的表项，若该位置之后存在表项，则将这些表项都前移一位。
     *
     * @param position 待移除的位置
     */
    public void removeItem(int position) {
        // 更新数据源
        dataSource.remove(position);
        //通知RecyclerView指定的表项被移除，刷新控件显示。
        notifyItemRemoved(position);
        /*
         * 通知RecyclerView刷新删除位置以及之后的表项，使得"onBindViewHolder()"方法重新执行。
         * 如果忘记执行此方法，删除位置以及之后的表项Position属性不会被更新，通过Position访问数据集可能会出错。
         */
        int itemCount = dataSource.size() - position;
        if (itemCount != 0) {
            notifyItemRangeChanged(position, itemCount);
        }
    }

    /**
     * Name        移动表项
     * Author      BI4VMR
     * Date        2022-4-10 23:50
     * Description 将指定的表项移动到目标位置。
     *
     * @param srcPosition 源表项所在的位置
     * @param dstPosition 目标位置
     */
    public void moveItem(int srcPosition, int dstPosition) {
        // 如果源位置与目标位置相同，直接退出当前方法。
        if (srcPosition == dstPosition) {
            return;
        }

        // 首先移除目标表项，并获取其内容。
        ItemBean item = dataSource.remove(srcPosition);
        // 然后根据源位置与目标位置的前后关系执行操作
        if (dstPosition - srcPosition > 0) {
            // 靠前的表项向列表后端移动
            // 该表项移除之后，后继表项全部向前移动一位，所以插入位置也要相对向前移动一位。
            dataSource.add(dstPosition - 1, item);
            // 更新被移动表项到被插入位置之间的表项
            int itemCount = dstPosition - srcPosition + 1;
            notifyItemRangeChanged(srcPosition, itemCount);
        } else {
            // 靠后的表项向列表前端移动
            // 该表项移除之后，由于插入位置不受影响，所以直接在该位置执行操作。
            dataSource.add(dstPosition, item);
            // 更新被移动表项到被插入位置之间的表项
            int itemCount = srcPosition - dstPosition + 1;
            notifyItemRangeChanged(dstPosition, itemCount);
        }
    }

    /**
     * Name        交换表项
     * Author      BI4VMR
     * Date        2022-4-10 22:32
     * Description 将源位置对应的表项与目标位置对应的表项交换。
     *
     * @param srcPosition 源表项所在的位置
     * @param dstPosition 目标表项所在的位置
     */
    public void swapItems(int srcPosition, int dstPosition) {
        // 如果源位置与目标位置相同，直接退出当前方法。
        if (srcPosition == dstPosition) {
            return;
        }
        // 更新数据源
        Collections.swap(dataSource, srcPosition, dstPosition);
        //通知RecyclerView表项被移动，刷新控件显示。
        notifyItemMoved(srcPosition, dstPosition);
        /*
         * 通知RecyclerView刷新两个交换位置之间的表项，使得"onBindViewHolder()"方法重新执行。
         * 如果忘记执行此方法，该区间内的表项Position属性不会被更新，通过Position访问数据集可能会出错。
         */
        if (dstPosition - srcPosition > 0) {
            // 靠前的表项与靠后的表项交换
            int itemCount = dstPosition - srcPosition + 1;
            notifyItemRangeChanged(srcPosition, itemCount);
        } else {
            // 靠后的表项与靠前的表项交换
            int itemCount = srcPosition - dstPosition + 1;
            notifyItemRangeChanged(dstPosition, itemCount);
        }
    }

    /**
     * Name        重新加载列表
     * Author      BI4VMR
     * Date        2022-4-10 22:48
     * Description 使用新的数据源替换RecyclerView中的现有数据。
     *
     * @param newDatas 新的数据源List
     */
    @SuppressLint("NotifyDataSetChanged")
    public void reloadItem(List<ItemBean> newDatas) {
        // 清空数据源
        dataSource.clear();
        // 重新填充数据源
        dataSource.addAll(newDatas);
        // 通知RecyclerView数据源改变
        notifyDataSetChanged();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_comment;
        ImageView iv_icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
