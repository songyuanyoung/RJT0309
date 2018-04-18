package com.ZachYang.rjt0309;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by zhangwenpurdue on 6/25/2017.
 */

public class Frag_Checkout_Confirm_Order extends Fragment{
    private static final String KEY = "extra";
    RequestQueue mQueue;
    StringRequest stringRequest;
    String orderID = "";
    public  static ArrayList<PlacedOrderDetail> placedOrdersList = PlacedOrdersList.getInstance();
    public  static ArrayList<OrderItem> orderList = OrderList.getInstance();

    Button confirm;

    public static Frag_Checkout_Confirm_Order newInstance(String extra) {
        Bundle args = new Bundle();
        args.putString(KEY, extra);
        Frag_Checkout_Confirm_Order fragment = new Frag_Checkout_Confirm_Order();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!= null) {
            orderID = bundle.getString(KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_confirm_order_layout, container, false);
        confirm = (Button) view.findViewById(R.id.button);
      //  Toast.makeText(getActivity(),orderList.size() + "", Toast.LENGTH_SHORT).show();
        comfirmAllOrders();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comfirmAllOrders();
               // Toast.makeText(getActivity(),placedOrdersList.size() + "", Toast.LENGTH_SHORT).show();
                Frag_Checkout_Recent_Orders frag_checkout_recent_orders = Frag_Checkout_Recent_Orders.newInstance("zach");

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_check_out,frag_checkout_recent_orders).commit();



            }
        });

        return view;
    }
    private void comfirmAllOrders() {
       // PlacedOrdersList.getInstance().clear();
        for (int i = 0; i < orderList.size(); i++) {
            isConfirmed(orderList.get(i));
        }


    }

    private void isConfirmed(OrderItem orderItem) {
        StringBuilder url = new StringBuilder("http://rjtmobile.com/aamir/grocery/MobileApp/order_confirmation.php?");

        url.append("&order_id=" + orderItem.getId_if_placed());

        mQueue = Volley.newRequestQueue(getActivity());

        stringRequest = new StringRequest(Request.Method.GET, url.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray placedItem = new JSONObject(response).getJSONArray("Order Detail");

                    for (int i = 0; i < placedItem.length(); i++) {
                        JSONObject item = placedItem.getJSONObject(i);
                        PlacedOrderDetail placedOrderDetail = new PlacedOrderDetail();
                        if (item.has("OrderId")) {
                            String gsId = item.getString("OrderId");
                            placedOrderDetail.setOrder_id(gsId);

                        }
                        if (item.has("OrderName")) {
                            String gName = item.getString("OrderName");
                            placedOrderDetail.setOrder_name(gName);

                        }
                        if (item.has("OrderQuantity")) {
                            String gQuantity = item.getString("OrderQuantity");
                            placedOrderDetail.setOrder_quantity(Integer.parseInt(gQuantity));

                        }
                        if (item.has("TotalOrder")) {
                            String total = item.getString("TotalOrder");
                            placedOrderDetail.setTotal_order(Integer.parseInt(total));
                        }
                        if (item.has("OrderDeliverAdd")) {
                            String DeliverAdd = item.getString("OrderDeliverAdd");
                            placedOrderDetail.setOrder_deliver_add(DeliverAdd);
                        }
                        if (item.has("OrderDate")) {
                            String date = item.getString("OrderDate");
                            placedOrderDetail.setOrder_date(date);
                        }
                        Log.d("url", placedOrderDetail.getOrder_name()+ placedOrderDetail.getOrder_id());

                        placedOrdersList.add(placedOrderDetail);


                    }

                   // Toast.makeText(getActivity(),placedOrdersList.size() + "", Toast.LENGTH_SHORT).show();




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("test", "exception");
            }
        });
        VolleyController.getInstance().addToRequestQueue(stringRequest, "hshs");


    }

}
