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
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.ItemVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Name        : Adapter06
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-12 09:55
 * Description : TODO 添加描述
 */
public class Adapter06 extends RecyclerView.Adapter<Adapter06.MyViewHolder> {

    // 数据源
    private List<ItemVO> dataSource;
    // 上下文环境
    private final Context mContext;

    public Adapter06(List<ItemVO> dataSource, Context context) {
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
        ItemVO item = dataSource.get(position);
        // 将数据设置到当前项的控件中
        holder.tvTitle.setText(item.getTitle());
        holder.tvInfo.setText(item.getInfo());
    }


    /* 将数据与ViewHolder绑定（局部刷新） */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.d("myapp", "onBindViewHolder() Position:" + position + " payloadsNum:" + payloads.size());
        // 如果载荷List内容为空，则执行普通的onBindViewHolder()方法。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Log.d("myapp", "payloads:" + payloads);
            // 短时间内多次更新同一表项时，"payloads"中可能有多个项，可以根据需要选择其中的一项。
            Object data = payloads.get(0);
            // 此处放置具体的局部更新逻辑，可以根据ViewType、载荷实际类型等条件进行判断。
            if (data instanceof ItemVO) {
                // 获取新数据的属性，并更新不为空的属性。
                ItemVO item = (ItemVO) data;
                if (item.getTitle() != null) {
                    holder.tvTitle.setText(item.getTitle());
                }
                if (item.getInfo() != null) {
                    holder.tvInfo.setText(item.getInfo());
                }
            }
        }
    }

    /* 获取表项的总数量 */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public List<ItemVO> getDataSource() {
        return dataSource;
    }

    /**
     * Name        获取数据源的一份副本
     * Author      BI4VMR
     * Date        2022-4-11 22:08
     * Description 因为数据源的更改会影响表项，有些操作不能直接在数据源上进行，可以在其副本上进行操作。
     *
     * @return 内含ItemVO的列表，是当前数据源的副本。
     */
    public List<ItemVO> getCopyOfDataSource() {
        List<ItemVO> newList = new ArrayList<>();
        for (int i = 0; i < dataSource.size(); i++) {
            // 创建新的对象，并复制原对象的属性。
            ItemVO item = dataSource.get(i);
            int id = item.getId();
            String title = item.getTitle();
            String comment = item.getInfo();
            newList.add(new ItemVO(id, title, comment));
        }
        return newList;
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
    public void reloadItem(List<ItemVO> newDatas) {
        // 清空数据源
        dataSource.clear();
        // 重新填充数据源
        dataSource.addAll(newDatas);
        // 通知RecyclerView数据源改变
        notifyDataSetChanged();
    }

    /**
     * Name        更新数据源
     * Author      BI4VMR
     * Date        2022-04-26 00:53
     * Description 使用新的数据源替换旧的数据源。
     *
     * @param newDatas 数据源
     */
    public void updateDataSource(List<ItemVO> newDatas) {
        dataSource = newDatas;
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