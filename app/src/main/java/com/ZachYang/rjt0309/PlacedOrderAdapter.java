package com.ZachYang.rjt0309;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by zhangwenpurdue on 6/26/2017.
 */

public class PlacedOrderAdapter extends RecyclerView.Adapter<PlacedOrderHolder>{
    String ORDER_DELIVER_ADD = "zach", curent_date = "6/25/2017", user_phone = "2193085517";
    private Context mContext;
    private ArrayList<PlacedOrderDetail> mList;

    public PlacedOrderAdapter(Context context, ArrayList<PlacedOrderDetail> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public PlacedOrderHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.placed_order_item_layout, parent, false);
        final PlacedOrderHolder holder = new PlacedOrderHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final PlacedOrderHolder holder, final int position) {
        final PlacedOrderDetail orderItem = mList.get(position);
        holder.mOrderID.setText( orderItem.getOrder_id());
        holder.mOrderName.setText(orderItem.getOrder_name());
        holder.mOrderQuantity.setText(orderItem.getOrder_quantity() + "");
        holder.mTotalOrder.setText(orderItem.getTotal_order() + "");
        holder.mOrderDeliveryAdd.setText(orderItem.getOrder_deliver_add());
        holder.mOrderDate.setText(orderItem.getOrder_date());




    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}



class PlacedOrderHolder extends RecyclerView.ViewHolder {
    TextView mOrderID, mOrderName, mOrderQuantity, mTotalOrder, mOrderDeliveryAdd, mOrderDate;

    public PlacedOrderHolder(View itemView) {
        super(itemView);

        mOrderID = (TextView) itemView.findViewById(R.id.orderid);
        mOrderName= (TextView)itemView.findViewById(R.id.ordername);
        mOrderQuantity = (TextView) itemView.findViewById(R.id.orderquantity);
        mTotalOrder = (TextView) itemView.findViewById(R.id.totalorder);
        mOrderDeliveryAdd = (TextView) itemView.findViewById(R.id.orderdeliveryaddr);
        mOrderDate = (TextView) itemView.findViewById(R.id.orderdate);

    }
}
