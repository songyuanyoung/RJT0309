package com.ZachYang.rjt0309;

import java.util.ArrayList;

/**
 * Created by Guanzhu Li on 3/9/2017.
 */
public class CategoryList extends ArrayList<Category> {
    private static CategoryList ourInstance = null;

    public synchronized static CategoryList getInstance() {

        if (ourInstance == null) {
            synchronized (CategoryList.class) {
                if (ourInstance == null) {
                    ourInstance = new CategoryList();
                }
            }
        }
        return ourInstance;
    }

    private CategoryList() {
    }
}
