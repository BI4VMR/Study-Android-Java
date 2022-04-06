package net.bi4vmr.study.ui_recyclerview_refreshitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Name          : ItemTwoBean
 * Author        : 詹屹罡
 * Date          : 2022-04-06 10:37
 * Description   : RecyclerView适配器
 */
public class MyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //数据源
    List<ItemBean> dataSource;
    //环境
    Context mContext;

    public MyListAdapter(List<ItemBean> dataSource, Context context) {
        // 外部传入为空时，初始化空的列表。
        if (dataSource == null) {
            dataSource = new ArrayList<>();
        }
        this.dataSource = dataSource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == ItemOneBean.VIEWTYPE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_one, parent, false);
            holder = new ItemOneVH(view);
        } else {
            // 默认返回最后一项的视图实例，防止出现异常。
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_two, parent, false);
            holder = new ItemTwoVH(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 获取当前项的数据
        ItemBean item = dataSource.get(position);
        // 根据Item的类型，将数据绑定到ViewHolder。
        if (item instanceof ItemOneBean) {
            ItemOneBean i1 = (ItemOneBean) item;
            ItemOneVH vh = (ItemOneVH) holder;
            vh.tv_title.setText(i1.getTitle());
            vh.tv_comment.setText(i1.getComment());
        } else {
            ItemTwoBean i2 = (ItemTwoBean) item;
            ItemTwoVH vh = (ItemTwoVH) holder;
            vh.tv_title.setText(i2.getTitle());
            vh.tv_comment.setText(i2.getComment());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        // 如果载荷List内容为空，执行普通onBindViewHolder()方法。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            // 此处只处理载荷List中的第一项，以此为例。
            Object data = payloads.get(0);
            // 根据表项的类型，执行更新操作。
            int type = getItemViewType(position);
            if (type == ItemOneBean.VIEWTYPE) {
                ItemOneBean newItem = (ItemOneBean) data;
                ItemOneVH vh = (ItemOneVH) holder;
                // 如果某个属性为空，说明不需要更新对应的控件。
                if (newItem.getTitle() != null) {
                    vh.tv_title.setText(newItem.getTitle());
                }
                if (newItem.getComment() != null) {
                    vh.tv_comment.setText(newItem.getComment());
                }


            } else if (type == ItemTwoBean.VIEWTYPE) {
                ItemTwoBean newItem = (ItemTwoBean) data;
                ItemTwoVH vh = (ItemTwoVH) holder;
                // 如果某个属性为空，说明不需要更新对应的控件。
                if (newItem.getTitle() != null) {
                    vh.tv_title.setText(newItem.getTitle());
                }
                if (newItem.getComment() != null) {
                    vh.tv_comment.setText(newItem.getComment());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * Name        : 获取Item的类型
     * Author      : 詹屹罡
     * Date        : 2022-4-6 11:26
     * Description : Bean类接口中的"getViewType"方法定义了Bean的类型，将其返回给RecyclerView。
     *
     * @param position 当前项在列表中的位置索引
     * @return 整形，当前项的ViewType。
     */
    @Override
    public int getItemViewType(int position) {
        return dataSource.get(position).getViewType();
    }

    /**
     * Name        : 局部更新Item的属性
     * Author      : 詹屹罡
     * Date        : 2022-4-6 14:45
     * Description : 更新Item的特定属性。
     *
     * @param position 待更新的位置索引
     * @param newItem  待更新的数据，类型需要与当前位置相匹配，属性的值为Null表示不需要更新，否则会被更新。
     */
    public void updateItemData(int position, ItemBean newItem) {
        // 根据当前位置的视图类型，更新对应参数。
        int type = getItemViewType(position);
        if (type == ItemOneBean.VIEWTYPE) {
            ItemOneBean rawData = (ItemOneBean) dataSource.get(position);
            ItemOneBean newData = (ItemOneBean) newItem;
            // 取出待更新的属性
            String title = newData.getTitle();
            String comment = newData.getComment();
            // 如果属性不为空，说明需要执行更新，否则将其忽略。
            if (title != null) {
                // 更新视图
                notifyItemChanged(position, newData);
                // 同步更新数据源
                rawData.setTitle(title);
            } else if (comment != null) {
                // 更新视图
                notifyItemChanged(position, newData);
                // 同步更新数据源
                rawData.setComment(comment);
            }
        } else if (type == ItemTwoBean.VIEWTYPE) {
            ItemTwoBean rawData = (ItemTwoBean) dataSource.get(position);
            ItemTwoBean newData = (ItemTwoBean) newItem;
            // 取出待更新的属性
            String title = newData.getTitle();
            String comment = newData.getComment();
            // 如果属性不为空，说明需要执行更新，否则将其忽略。
            if (title != null) {
                // 更新视图
                notifyItemChanged(position, newData);
                // 同步更新数据源
                rawData.setTitle(title);
            } else if (comment != null) {
                // 更新视图
                notifyItemChanged(position, newData);
                // 同步更新数据源
                rawData.setComment(comment);
            }
        }
    }

    /**
     * 类型一的ViewHolder类
     */
    static class ItemOneVH extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_comment;
        ImageView iv_icon;

        public ItemOneVH(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }

    /**
     * 类型二的ViewHolder类
     */
    static class ItemTwoVH extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_comment;

        public ItemTwoVH(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_comment = itemView.findViewById(R.id.tv_comment);
        }
    }
}
