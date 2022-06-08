package net.bi4vmr.study.ui.component.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Name        : TestFragment
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-05-30 22:40
 * Description : 测试Fragment
 */
public class TestFragment extends Fragment {

    private static final String ARG_NAME_TEXTINFO = "TEXTINFO";

    private String textInfo;

    public TestFragment() {
        // 此处应当留空
    }

    /**
     * 获取Fragment实例
     *
     * @param textInfo 初始参数：文本信息
     * @return 当前Fragment的实例
     */
    public static TestFragment newInstance(String textInfo) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME_TEXTINFO, textInfo);
        // 向Fragment传入参数Bundle
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取参数Bundle对象
        Bundle args = getArguments();
        if (args != null) {
            // 从Bundle中取出参数
            textInfo = args.getString(ARG_NAME_TEXTINFO);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 创建View实例
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        // 初始化View中的控件
        TextView tv = view.findViewById(R.id.tvContent);
        if (textInfo == null) {
            tv.setText("TestFragment");
        } else {
            tv.setText(textInfo);
        }
        // 返回View实例
        return view;
    }
}
