package com.ZachYang.rjt0309;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GroceryActivity extends AppCompatActivity {
    private static final String TAG = GroceryActivity.class.getClass().getSimpleName();
    Frag_Home frag_home;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_main);

        frag_home =Frag_Home.newInstance("");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main, frag_home).commit();
        getSupportFragmentManager().beginTransaction().addToBackStack(null);

    }
}
