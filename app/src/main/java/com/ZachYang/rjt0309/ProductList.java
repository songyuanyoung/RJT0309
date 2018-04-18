package com.ZachYang.rjt0309;

import java.util.ArrayList;

/**
 * Created by zhangwenpurdue on 6/24/2017.
 */

public class ProductList extends ArrayList<Product> {
    private static ProductList ourInstance = null;

    public synchronized static ProductList getInstance() {

        if (ourInstance == null) {
            synchronized (CategoryList.class) {
                if (ourInstance == null) {
                    ourInstance = new ProductList();
                }
            }
        }
        return ourInstance;
    }

    private ProductList() {
    }
}
