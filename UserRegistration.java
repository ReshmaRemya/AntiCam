package com.example.anticam.User;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anticam.Common.Utility;
import com.example.anticam.R;
import com.example.anticam.login;

import java.util.HashMap;
import java.util.Map;

public class UserRegistration extends AppCompatActivity {

    TextView log;
    EditText name,aadhar,phone,email,password;
    RadioGroup rdgroup;
    Button btnreg;
    String NAME,GENDER,AADHAR,PHONE,EMAIL,PASS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_user_registration);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        log=findViewById(R.id.user_reg_login);
        name=findViewById(R.id.user_reg_name);
        aadhar=findViewById(R.id.user_reg_aadhar);
        phone=findViewById(R.id.user_reg_phone);
        email=findViewById(R.id.user_reg_email);
        password=findViewById(R.id.user_reg_password);
        btnreg=findViewById(R.id.user_reg_btnreg);

        rdgroup=findViewById(R.id.user_reg_rdgroup);

        log.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(new Intent(getApplicationContext(), login.class));
                return false;
            }
        });

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NAME=name.getText().toString();
                int id=rdgroup.getCheckedRadioButtonId();
                RadioButton rdtemp=findViewById(id);
                GENDER=rdtemp.getText().toString();
                AADHAR=aadhar.getText().toString();
                PHONE=phone.getText().toString();
                EMAIL=email.getText().toString();
                PASS=password.getText().toString();



                if(NAME.isEmpty()){
                    name.requestFocus();
                    name.setError("Enter Name");
                }else if(GENDER.isEmpty()){
                    Toast.makeText(UserRegistration.this, "Select Gender", Toast.LENGTH_SHORT).show();
                }else if(AADHAR.isEmpty() || AADHAR.length()!=12){
                    aadhar.requestFocus();
                    aadhar.setError("Enter Aadhar Number");
                }else if(PHONE.isEmpty() || PHONE.length() < 10 || PHONE.length() > 10){
                    phone.requestFocus();
                    phone.setError("Enter Phone");
                }else if(!EMAIL.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")){
                    email.requestFocus();
                    email.setError("Enter Email");
                }else  if(PASS.isEmpty()){
                    password.requestFocus();
                    password.setError("Enter Password");
                }else{
                    User_Reg();
                }

            }
        });


    }

    public void User_Reg() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {

                    Toast.makeText(getApplicationContext(), "Registration success..!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), login.class));

                } else {
                    Toast.makeText(getApplicationContext(), "failed..!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "reg_user");
                map.put("name", NAME);
                map.put("gender", GENDER);
                map.put("aadhar", AADHAR);
                map.put("phone", PHONE);
                map.put("email", EMAIL);
                map.put("password", PASS);

                return map;
            }
        };
        queue.add(request);
    }


}
