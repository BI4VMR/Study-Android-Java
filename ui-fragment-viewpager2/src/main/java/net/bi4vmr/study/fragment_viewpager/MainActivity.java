package net.bi4vmr.study.fragment_viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_content);
        ViewPager2 viewPager2 = findViewById(R.id.vp_content);

        // 创建适配器的数据源
        List<MyFragment> pages = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            MyFragment fragment = MyFragment.newInstance("页面" + (i + 1));
            pages.add(fragment);
        }
        //为ViewPager设置适配器
        MyVPAdapter adapter = new MyVPAdapter(this, pages);
        viewPager2.setAdapter(adapter);

        /*
         * 设置预加载页面的数量
         *
         * 默认值为"-1"，即不进行预加载；设置为N个页面时，系统除了加载当前页面，还会额外分别加载该页面两侧的N个页面。
         */
        viewPager2.setOffscreenPageLimit(1);

        /*
         * 设置滚动方向
         */
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        /*
         * 是否允许用户触摸翻页
         */
        viewPager2.setUserInputEnabled(false);

        //禁用缓存与预读取
//        ((RecyclerView) vp2.getChildAt(0)).getLayoutManager().setItemPrefetchEnabled(false);
//        ((RecyclerView) vp2.getChildAt(0)).setItemViewCacheSize(0);

        //将TabLayout与ViewPager绑定
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("页面" + (position + 1));
            }
        }).attach();
    }
}
