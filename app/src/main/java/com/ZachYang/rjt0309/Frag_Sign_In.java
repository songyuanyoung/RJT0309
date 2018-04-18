package com.ZachYang.rjt0309;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.ArrayList;

/**
 * Created by zhangwenpurdue on 6/25/2017.
 */

public class Frag_Sign_In extends Fragment implements View.OnClickListener{
    User_Current current_user = User_Current.getInstance();
    private static final String KEY = "extra";
    private final String BASE_URL = "http://rjtmobile.com/aamir/grocery/MobileApp/grocery_list.php?";
    TextView tv_signup;
    TextView tv_resetps;
    EditText et_loginPassword;
    EditText et_loginPhone;
    Button btn_signIn;
    RequestQueue mQueue;
    StringRequest stringRequest;
    StringBuilder url =new StringBuilder( "http://rjtmobile.com/aamir/grocery/MobileApp/grocery_login.php?");

    public static Frag_Sign_In newInstance(String extra) {
        Bundle args = new Bundle();
        args.putString(KEY, extra);
        Frag_Sign_In fragment = new Frag_Sign_In();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_layout, container, false);
        tv_resetps = (TextView) view.findViewById(R.id.loginReset);
        tv_signup = (TextView) view.findViewById(R.id.loginSignup);

        et_loginPhone = (EditText) view.findViewById(R.id.loginPhone);
        et_loginPassword = (EditText) view.findViewById(R.id.loginPassword);
        btn_signIn = (Button) view.findViewById(R.id.loginBtn);


tv_resetps.setOnClickListener(this);
        tv_signup.setOnClickListener(this);
        btn_signIn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            StringBuilder phone = new StringBuilder("&user_phone=");
            StringBuilder password = new StringBuilder("&user_password=");
            url = new StringBuilder("http://rjtmobile.com/aamir/grocery/MobileApp/grocery_login.php?");

            phone.append( et_loginPhone.getText().toString().trim());
            password.append( et_loginPassword.getText().toString().trim());
            url.append(phone.toString());
            url.append(password.toString());
            mQueue = Volley.newRequestQueue(getActivity());
            stringRequest = new StringRequest(url.toString(),
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            if(response.trim().contains("success")){
                                current_user.setUser_phone(et_loginPhone.getText().toString().trim());
                                current_user.setUser_password(et_loginPassword.getText().toString().trim());
                                //openProfile();
                                Intent intent = new Intent(getActivity(), GroceryActivity.class);
                                //Toast.makeText(getActivity(),url,Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            }else{
                                Toast.makeText(getActivity(),"Fail to login",Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });

            mQueue.add(stringRequest);
        }
    });









        return view;
}


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.loginSignup:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.replace, Frag_Sign_Up.newInstance("Zach")).commit();

                break;
            case R.id.loginReset:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.replace, Frag_Reset_Password.newInstance("Zach")).commit();
                break;
        }
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
    }
}



