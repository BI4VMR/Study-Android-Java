package net.bi4vmr.study.recyclerview_base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //数据源
    List<ItemBean> dataSource;
    //环境
    Context mContext;

    public MyAdapter(List<ItemBean> dataSource, Context context) {
        this.dataSource = dataSource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //获取当前项的数据
        ItemBean item = dataSource.get(position);
        //将数据设置到当前项的控件中
        holder.tv_title.setText(item.getTitle());
        holder.tv_comment.setText(item.getComment());
        holder.iv_icon.setImageResource(R.drawable.ic_launcher_foreground);
        holder.iv_icon.setBackgroundResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
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
