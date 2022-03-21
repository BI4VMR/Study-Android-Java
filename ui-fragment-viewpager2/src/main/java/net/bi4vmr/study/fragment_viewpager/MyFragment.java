package net.bi4vmr.study.fragment_viewpager;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {

    private static final String ARG_TEXT = "text";

    private String text;

    public static MyFragment newInstance(String text) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    public MyFragment() {
        // 此处应当留空
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("myapp", text + ":onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
        }
        Log.i("myapp", text + ":onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        TextView tv_text = view.findViewById(R.id.tv_content);
        tv_text.setText(text);
        Log.i("myapp", text + ":onCreateView()");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("myapp", text + ":onViewCreated()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("myapp", text + ":onViewStateRestored()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("myapp", text + ":onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("myapp", text + ":onResume()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("myapp", text + ":onHiddenChanged()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("myapp", text + ":onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("myapp", text + ":onStop()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("myapp", text + ":onSaveInstanceState()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("myapp", text + ":onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("myapp", text + ":onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myapp", text + ":onDestroy()");
    }
}