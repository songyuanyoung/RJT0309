package com.ZachYang.rjt0309;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by zhangwenpurdue on 6/25/2017.
 */

public class Frag_Checkout_Recent_Orders extends Fragment{
    private static final String KEY = "extra";
    private RecyclerView mRecyclerView;
    public static ArrayList<PlacedOrderDetail> list = PlacedOrdersList.getInstance();
    public static Frag_Checkout_Recent_Orders newInstance(String extra) {
        Bundle args = new Bundle();
        args.putString(KEY, extra);
        Frag_Checkout_Recent_Orders fragment = new Frag_Checkout_Recent_Orders();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recent_order_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        mRecyclerView.setAdapter(new PlacedOrderAdapter(getActivity(), list));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
