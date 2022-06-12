package net.bi4vmr.study.ui.extctrl.recyclerview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.ui.extctrl.recyclerview.R;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type1VO;
import net.bi4vmr.study.ui.extctrl.recyclerview.vo.Type2VO;

import java.util.List;

/**
 * Name        : Adapter03
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-11 11:11
 * Description : Demo03所使用的适配器。
 */
public class Adapter07 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 数据源
    private final List<Object> dataSource;
    // 上下文环境
    private final Context mContext;

    public Adapter07(List<Object> dataSource, Context context) {
        this.dataSource = dataSource;
        this.mContext = context;
    }

    /* 创建ViewHolder */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("myapp", "onCreateViewHolder() 表项类型:" + viewType);
        RecyclerView.ViewHolder vh;
        if (viewType == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_type1, parent, false);
            vh = new Type1VH(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_type2, parent, false);
            vh = new Type2VH(view);
        } else {
            throw new IllegalArgumentException();
        }

        return vh;
    }

    /* 将数据与ViewHolder绑定 */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("myapp", "onBindViewHolder() 表项位置:" + (position + 1));
        int viewType = getItemViewType(position);
        if (viewType == 1) {
            // 获取当前项的数据
            Type1VO item = (Type1VO) dataSource.get(position);
            // 将数据设置到当前项的控件中
            Type1VH vh = (Type1VH) holder;
            vh.tvTitle.setText(item.getTitle());
            vh.tvInfo.setText(item.getInfo());
        } else if (viewType == 2) {
            // 获取当前项的数据
            Type2VO item = (Type2VO) dataSource.get(position);
            // 将数据设置到当前项的控件中
            Type2VH vh = (Type2VH) holder;
            vh.tvInfo.setText(item.getInfo());
        }
    }

    /* 获取表项的总数量 */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /* 获取当前位置的Item类型 */
    @Override
    public int getItemViewType(int position) {
        Object obj = dataSource.get(position);
        // 根据数据源中的对象类型，决定返回的viewType值。
        if (obj instanceof Type1VO) {
            return 1;
        } else if (obj instanceof Type2VO) {
            return 2;
        } else {
            return -1;
        }
    }

    /* 类型一的ViewHolder类 */
    static class Type1VH extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvInfo;

        public Type1VH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvInfo = itemView.findViewById(R.id.tvInfo);
        }
    }

    /* 类型二的ViewHolder类 */
    static class Type2VH extends RecyclerView.ViewHolder {
        TextView tvInfo;

        public Type2VH(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tvInfo);
        }
    }
}
