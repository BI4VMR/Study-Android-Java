package net.bi4vmr.study.fragment_dynamic;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Function1Fragment extends Fragment {

    public Function1Fragment() {
    }

    public static Function1Fragment newInstance() {
        return new Function1Fragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("myapp", "Fun1:onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myapp", "Fun1:onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("myapp", "Fun1:onCreateView()");
        return inflater.inflate(R.layout.fragment_function1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("myapp", "Fun1:onViewCreated()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("myapp", "Fun1:onViewStateRestored()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("myapp", "Fun1:onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("myapp", "Fun1:onResume()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("myapp", "Fun1:onHiddenChanged()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("myapp", "Fun1:onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("myapp", "Fun1:onStop()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("myapp", "Fun1:onSaveInstanceState()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("myapp", "Fun1:onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("myapp", "Fun1:onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myapp", "Fun1:onDestroy()");
    }
}