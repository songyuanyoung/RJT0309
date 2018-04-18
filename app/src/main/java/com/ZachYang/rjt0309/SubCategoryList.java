package com.ZachYang.rjt0309;

import java.util.ArrayList;

/**
 * Created by zhangwenpurdue on 6/24/2017.
 */

public class SubCategoryList extends ArrayList<SubCategory> {
    private static SubCategoryList ourInstance = null;

    public synchronized static SubCategoryList getInstance() {

        if (ourInstance == null) {
            synchronized (CategoryList.class) {
                if (ourInstance == null) {
                    ourInstance = new SubCategoryList();
                }
            }
        }
        return ourInstance;
    }

    private SubCategoryList() {
    }
}
