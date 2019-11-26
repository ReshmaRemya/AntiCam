package com.example.anticam.User;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anticam.Common.Utility;
import com.example.anticam.R;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Add_Complaint extends Fragment {

    String SUB,DES;
    EditText sub,des;
    Button send;
    public Add_Complaint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add__complaint, container, false);

        sub=(EditText)view.findViewById(R.id.usercompliant_subject);
        des=(EditText)view.findViewById(R.id.usercompliant_details);
        send=(Button)view.findViewById(R.id.usercompliant_btnsend);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SUB=sub.getText().toString().trim();
                DES=des.getText().toString().trim();


                if (SUB.isEmpty()) {
                    sub.requestFocus();
                    sub.setError("ENTER VALID SUBJECT");
                } else if (DES.isEmpty()) {
                    des.requestFocus();
                    des.setError("ENTER VALID DETAILS");
                } else {
                    addComplaint(SUB,DES);
                }
            }
        });









        return view;
    }
    public void addComplaint(final String ...arr)
    {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {
                    Toast.makeText(getContext(), "Complaint added  ..!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(),UserHome.class));

                }
                else
                {
                    Toast.makeText(getContext(), "eroor ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
                map.put("key","addComplaint");
                map.put("uid",uid);
                map.put("subject",arr[0]);
                map.put("complaint",arr[1]);



                return map;
            }
        };
        queue.add(request);
    }

}