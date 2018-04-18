package com.ZachYang.rjt0309;

/**
 * Created by zhangwenpurdue on 6/26/2017.
 */

public class PlacedOrderDetail {
    private String order_id;
    private String order_name;
    private int order_quantity;
    private int total_order;
    private String order_deliver_add;
    private String order_date;

    public PlacedOrderDetail() {
    }

    public PlacedOrderDetail(String order_id, String order_name, int order_quantity, int total_order, String order_deliver_add, String order_date) {
        this.order_id = order_id;
        this.order_name = order_name;
        this.order_quantity = order_quantity;
        this.total_order = total_order;
        this.order_deliver_add = order_deliver_add;
        this.order_date = order_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public int getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity) {
        this.order_quantity = order_quantity;
    }

    public int getTotal_order() {
        return total_order;
    }

    public void setTotal_order(int total_order) {
        this.total_order = total_order;
    }

    public String getOrder_deliver_add() {
        return order_deliver_add;
    }

    public void setOrder_deliver_add(String order_deliver_add) {
        this.order_deliver_add = order_deliver_add;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
}
