package com.ZachYang.rjt0309;

/**
 * Created by zhangwenpurdue on 6/26/2017.
 */

public class User_Current extends User {
    private static User_Current ourInstance = null;

    public synchronized static User_Current getInstance() {

        if (ourInstance == null) {
            synchronized (User_Current.class) {
                if (ourInstance == null) {
                    ourInstance = new User_Current();
                }
            }
        }
        return ourInstance;
    }

    private User_Current() {
    }
}
