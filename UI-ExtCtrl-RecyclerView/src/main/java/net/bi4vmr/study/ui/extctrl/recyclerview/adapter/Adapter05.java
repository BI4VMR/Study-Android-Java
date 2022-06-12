package net.bi4vmr.study.ui.extctrl.recyclerview.adapter;

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

import java.util.List;

/**
 * Name        : Adapter01
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-10 00:03
 * Description : Demo01所使用的适配器。
 */
public class Adapter05 extends RecyclerView.Adapter<Adapter05.MyViewHolder> {

    // 数据源
    private final List<Type1VO> dataSource;
    // 上下文环境
    private final Context mContext;

    public Adapter05(List<Type1VO> dataSource, Context context) {
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
        holder.tvComment.setText(item.getInfo());
    }


    /* 将数据与ViewHolder绑定（局部刷新） */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.d("myapp", "onBindViewHolder() Position:" + position + " payloadsNum:" + payloads.size());
        // 如果载荷List内容为空，则执行普通的onBindViewHolder()方法。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            // 短时间内多次更新同一表项时，"payloads"中可能有多个项，可以根据需要选择其中的一项。
            Object data = payloads.get(0);
            // 此处放置具体的局部更新逻辑，可以根据ViewType、载荷实际类型等条件进行判断。
            if (data instanceof Type1VO) {
                // 获取新数据的属性，并更新不为空的属性。
                Type1VO item = (Type1VO) data;
                if (item.getTitle() != null) {
                    holder.tvTitle.setText(item.getTitle());
                }
                if (item.getInfo() != null) {
                    holder.tvComment.setText(item.getInfo());
                }
            }
        }
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
        notifyItemChanged(position, item);
    }

    /* ViewHolder类，用于表项的复用。 */
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvComment;
        ImageView ivIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvComment = itemView.findViewById(R.id.tvInfo);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}
