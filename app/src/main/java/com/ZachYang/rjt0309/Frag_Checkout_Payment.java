package com.ZachYang.rjt0309;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by zhangwenpurdue on 6/25/2017.
 */

public class Frag_Checkout_Payment extends Fragment{
    private static final String KEY = "extra";
    String orderID = "";
    Button payment;
    public static Frag_Checkout_Payment newInstance(String extra) {
        Bundle args = new Bundle();
        args.putString(KEY, extra);
        Frag_Checkout_Payment fragment = new Frag_Checkout_Payment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!= null) {
            orderID = bundle.getString("orderID");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_payment_layout, container, false);
        payment = (Button) view.findViewById(R.id.paywithpaypal);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag_Checkout_Confirm_Order frag_checkout_confirm_order = Frag_Checkout_Confirm_Order.newInstance("zach");
                Bundle bundle = new Bundle();
                bundle.putString("orderID", orderID);
                frag_checkout_confirm_order.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_check_out, frag_checkout_confirm_order).commit();

            }
        });
        return view;
    }
}
