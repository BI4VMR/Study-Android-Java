package net.bi4vmr.study.ui.extctrl.recyclerview.adapter;

import android.content.Context;
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
 * Description : Demo01的适配器。
 */
public class Adapter01 extends RecyclerView.Adapter<Adapter01.MyViewHolder> {

    // 数据源
    private final List<Type1VO> dataSource;
    // 上下文环境
    private final Context mContext;

    public Adapter01(List<Type1VO> dataSource, Context context) {
        this.dataSource = dataSource;
        this.mContext = context;
    }

    /* 创建ViewHolder */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_type1, parent, false);
        return new MyViewHolder(view);
    }

    /* 将数据与ViewHolder绑定 */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // 获取当前项的数据
        Type1VO item = dataSource.get(position);
        // 将数据设置到当前项的控件中
        holder.tvTitle.setText(item.getTitle());
        holder.tvComment.setText(item.getComment());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
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
