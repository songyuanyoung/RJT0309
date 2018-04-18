package com.ZachYang.rjt0309;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangwenpurdue on 6/25/2017.
 */

public class Frag_Reset_Password extends Fragment{
    private static final String KEY = "extra";
    public static Frag_Reset_Password newInstance(String extra) {
        Bundle args = new Bundle();
        args.putString(KEY, extra);
        Frag_Reset_Password fragment = new Frag_Reset_Password();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reset_password_layout, container, false);
        return view;
    }
}
