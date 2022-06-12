package net.bi4vmr.study.ui.extctrl.viewpager2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        ViewPager2 viewPager2 = findViewById(R.id.vp2Content);

        // 创建测试页面
        List<TestFragment> pages = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            pages.add(TestFragment.newInstance("页面" + (i + 1)));
        }

        // 为ViewPager设置适配器
        Adapter01 adapter = new Adapter01(this, pages);
        viewPager2.setAdapter(adapter);

        /*
         * 设置预加载页面的数量
         *
         * 默认值为"-1"，即不进行预加载；设置为N个页面时，系统除了加载当前页面，还会额外分别加载该页面两侧的N个页面。
         */
        // viewPager2.setOffscreenPageLimit(1);

        /*
         * 设置滚动方向
         */
        // viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        /*
         * 是否允许用户触摸翻页
         */
        // viewPager2.setUserInputEnabled(false);

        //禁用缓存与预读取
//        ((RecyclerView) vp2.getChildAt(0)).getLayoutManager().setItemPrefetchEnabled(false);
//        ((RecyclerView) vp2.getChildAt(0)).setItemViewCacheSize(0);
    }
}