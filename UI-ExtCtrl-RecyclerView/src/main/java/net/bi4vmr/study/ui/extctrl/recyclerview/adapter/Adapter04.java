package net.bi4vmr.study.ui.extctrl.recyclerview.adapter;

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

import net.bi4vmr.study.ui.extctrl.recyclerview.R;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type1VO;

import java.util.Collections;
import java.util.List;

/**
 * Name        : Adapter04
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-11 22:03
 * Description : Demo04所使用的适配器。
 */
public class Adapter04 extends RecyclerView.Adapter<Adapter04.MyViewHolder> {

    // 数据源
    private final List<Type1VO> dataSource;
    // 上下文环境
    private final Context mContext;

    public Adapter04(List<Type1VO> dataSource, Context context) {
        this.dataSource = dataSource;
        this.mContext = context;
    }

    /* 创建ViewHolder */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("myapp", "onCreateViewHolder() ViewType:" + viewType);
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_type1, parent, false);
        return new MyViewHolder(view);
    }

    /* 将数据与ViewHolder绑定 */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("myapp", "onBindViewHolder() Position:" + position);
        // 获取当前项的数据
        Type1VO item = dataSource.get(position);
        // 将数据设置到当前项的控件中
        holder.tvTitle.setText(item.getTitle());
        holder.tvInfo.setText(item.getInfo());
        // 设置View的点击事件
        holder.itemView.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            Log.d("myapp", "点击第" + (currentPosition + 1) + "项");
        });
    }

    /* 获取表项的总数量 */
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
    public void updateItem(int position, Type1VO item) {
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
    public void addItem(int position, Type1VO item) {
        // 更新数据源
        dataSource.add(position, item);
        // 通知RecyclerView新的表项被插入，刷新控件显示。
        notifyItemInserted(position);
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

        // 更新数据源
        Collections.swap(dataSource, srcPosition, dstPosition);
        //通知RecyclerView表项被移动，刷新控件显示。
        notifyItemMoved(srcPosition, dstPosition);
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
    public void reloadItem(List<Type1VO> newDatas) {
        // 清空数据源
        dataSource.clear();
        // 重新填充数据源
        dataSource.addAll(newDatas);
        // 通知RecyclerView数据源改变
        notifyDataSetChanged();
    }

    /* ViewHolder类，用于表项的复用。 */
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvInfo;
        ImageView ivIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvInfo = itemView.findViewById(R.id.tvInfo);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}