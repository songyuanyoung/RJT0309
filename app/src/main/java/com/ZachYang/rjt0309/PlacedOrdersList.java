package com.ZachYang.rjt0309;

import java.util.ArrayList;

/**
 * Created by zhangwenpurdue on 6/26/2017.
 */

public class PlacedOrdersList extends ArrayList<PlacedOrderDetail> {
    private static PlacedOrdersList ourInstance = null;

    public synchronized static PlacedOrdersList getInstance() {

        if (ourInstance == null) {
            synchronized (PlacedOrdersList.class) {
                if (ourInstance == null) {
                    ourInstance = new PlacedOrdersList();
                }
            }
        }
        return ourInstance;
    }

    private PlacedOrdersList() {
    }
}
