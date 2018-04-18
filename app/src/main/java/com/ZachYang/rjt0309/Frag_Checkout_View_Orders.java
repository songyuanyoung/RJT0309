package com.ZachYang.rjt0309;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by zhangwenpurdue on 6/25/2017.
 */

public class Frag_Checkout_View_Orders extends Fragment {
    private ArrayList<OrderItem> myorders = OrderList.getInstance();
    private RecyclerView mRecyclerView;
    private static final String KEY = "extra";
    Button checkOutNow;
    TextView totalAmout;
    RequestQueue mQueue;
    StringRequest stringRequest;
    private  ArrayList<PlacedOrderDetail> myPlacedOrder = PlacedOrdersList.getInstance();

    public  String KEY_ORDER_CATEGORY = "&order_category=";
    public  String KEY_ORDER_NAME = "&order_name=";
    public  String KEY_ORDER_QUANTITY = "&order_quantity=";
    public  String KEY_TOTAL_ORDER = "&total_order=";
    public  String KEY_ORDER_DELIVERY_ADDR = "&order_delivery_add=";
    public  String KEY_ORDER_DATE = "&order_date=";
    public  String KEY_USER_PHONE = "&user_phone=";
    String totalAmount = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_view_orders_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.orders_recycler);
        mRecyclerView.setAdapter(new OrdersAdapter(getActivity(), myorders));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        checkOutNow = (Button) view.findViewById(R.id.chechOutNow);
        totalAmout = (TextView) view.findViewById(R.id.totalAmount);
        totalAmount = calculateTotal();
        totalAmout.setText("Total: $" + totalAmount);
        checkOutNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeWholeOrder();
                Frag_Checkout_Add_address frag_checkout_add_address = Frag_Checkout_Add_address.newInstance("zach");
                Bundle bundle = new Bundle();
                bundle.putString("totalAmount", totalAmount);
                frag_checkout_add_address.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_check_out, frag_checkout_add_address).commit();



            }
        });
        return view;
    }
    private  void placeWholeOrder() {
        for (int i = 0; i < myorders.size(); i++) {
            placeOneOrder(myorders.get(i));
        }
    }
    private void placeOneOrder(final OrderItem orderItem) {
        StringBuilder url = new StringBuilder("http://rjtmobile.com/aamir/grocery/MobileApp/order_request.php?");
        User_Current user_current = User_Current.getInstance();
        KEY_ORDER_CATEGORY += orderItem.getOrder_category();
        KEY_ORDER_NAME += orderItem.getOrder_name();
        KEY_ORDER_QUANTITY += orderItem.getOrder_quantity();
        KEY_TOTAL_ORDER += orderItem.getTotal_order();
        KEY_ORDER_DELIVERY_ADDR += orderItem.getOrder_deliver_add();
        KEY_ORDER_DATE += orderItem.getOrder_date();
        KEY_USER_PHONE += user_current.getUser_phone();

        url.append(KEY_ORDER_CATEGORY);
        url.append(KEY_ORDER_NAME);
        url.append(KEY_ORDER_QUANTITY);
        url.append(KEY_TOTAL_ORDER);
        url.append( KEY_ORDER_DELIVERY_ADDR);
        url.append(KEY_ORDER_DATE);
        url.append(KEY_USER_PHONE);
        mQueue = Volley.newRequestQueue(getActivity());
        stringRequest = new StringRequest(url.toString(),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        String orderPre = "order confirmed. order id is : ";
                        String orderId = response.substring(orderPre.length(), response.length());

                        if(response.trim().contains("order confirmed")){

                            orderItem.setId_if_placed(orderId);
                          //  getActivity().startActivity(intent);
                            //  Frag_Checkout_Payment frag_checkout_payment = Frag_Checkout_Payment.newInstance("Zach");
                            //    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_check_out,frag_checkout_payment).commit();
                        }else{
                            Toast.makeText(getActivity(),"Fail to login",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        mQueue.add(stringRequest);











    }
    private String getURL() {
        User_Current user_current = User_Current.getInstance();
        String tempUrl = "";
        String tempTotalOrder = calculateTotal();
        String tempOrderDeliveryAdd = "Delivery";
        String tempDate = "10-01-2017";
        for (int i = 0; i < myorders.size(); i++) {
            String tempCategory = KEY_ORDER_CATEGORY + myorders.get(i).getOrder_category();
            String tempName = KEY_ORDER_NAME + myorders.get(i).getOrder_name();
            String tempQuantity = KEY_ORDER_QUANTITY + myorders.get(i).getOrder_quantity();
            tempUrl += (tempCategory + tempName + tempQuantity);
        }

        return tempUrl;
    }

    private String calculateTotal() {
        int result = 0;
        for (int i = 0; i < myorders.size(); i++) {
            int price = Integer.parseInt(myorders.get(i).getOrder_price().toString());
            int quantity = myorders.get(i).getOrder_quantity();
            result += (price * quantity);
        }

        return result + "";
    }
    public static Frag_Checkout_View_Orders newInstance(String extra) {
        Bundle args = new Bundle();
        args.putString(KEY, extra);
        Frag_Checkout_View_Orders fragment = new Frag_Checkout_View_Orders();
        fragment.setArguments(args);
        return fragment;
    }
}
