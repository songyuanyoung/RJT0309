package com.ZachYang.rjt0309;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SignInUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_up);
        getSupportFragmentManager().beginTransaction().add(R.id.replace, Frag_Sign_In.newInstance("Zach")).commit();
        getSupportFragmentManager().beginTransaction().addToBackStack(null);
    }
}
