package net.bi4vmr.study.ui_recyclerview_diffutil;

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
     * Name        获取表项数量
     * Author      BI4VMR
     * Date        2022-4-11 22:12
     * Description 获取表项的数量。
     *
     * @return 数据源中项目的数量
     */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * Name        获取数据源的一份副本
     * Author      BI4VMR
     * Date        2022-4-11 22:08
     * Description 因为数据源的更改会影响表项，有些操作不能直接在数据源上进行，可以在其副本上进行操作。
     *
     * @return 内含ItemBean的列表，是当前数据源的副本。
     */
    public List<ItemBean> getCopyOfDataSource() {
        List<ItemBean> newList = new ArrayList<>();
        for (int i = 0; i < dataSource.size(); i++) {
            // 创建新的对象，并复制原对象的属性。
            ItemBean item = dataSource.get(i);
            int id = item.getId();
            String title = item.getTitle();
            String comment = item.getComment();
            newList.add(new ItemBean(id, title, comment));
        }
        return newList;
    }

    public void changeDataSource(List<ItemBean> newDatas) {
        dataSource = newDatas;
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
