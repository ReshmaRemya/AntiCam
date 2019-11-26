package com.example.anticam.User;


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
public class LinkBanks extends Fragment {


    EditText cardno, cardcvv, cardpin, cardbal;
    Button btn;
    String cartno, cname, ccc, cpin, bal;
    String Scardnum, Scvv, Spin, Sbal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_link_banks, container, false);


        cardno = (EditText) view.findViewById(R.id.linkbank_cardno);
        cardcvv = (EditText) view.findViewById(R.id.linkbank_cvv);
        cardpin = (EditText) view.findViewById(R.id.linkbank_pin);
        cardbal = (EditText) view.findViewById(R.id.linkbank_balance);
        btn = (Button) view.findViewById(R.id.linkbank_btnadd);

        getACcountDetails();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Scardnum = cardno.getText().toString().trim();
                Scvv = cardcvv.getText().toString().trim();
                Spin = cardpin.getText().toString().trim();
                Sbal = cardbal.getText().toString().trim();

                if ((cardno.getText().toString().isEmpty())) {

                    cardno.setError("Number");
                } else if ((cardcvv.getText().toString().isEmpty())) {

                    cardcvv.setError("CVV Please");
                } else if ((cardpin.getText().toString().isEmpty())) {

                    cardpin.setError("PIN required");
                } else if ((cardbal.getText().toString().isEmpty())) {

                    cardbal.setError("Enter Balance");
                } else {

                    //Toast.makeText(getContext(), "ividethi", Toast.LENGTH_SHORT).show();

                    addACcount();
                }


            }
        });



        return view;
    }

    public void addACcount() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                // Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();

                try {
                    if (!response.trim().equals("failed")) {

                        String data = response;

                        Toast.makeText(getContext(), "ok", Toast.LENGTH_LONG).show();


                        LinkBanks linkBanks = new LinkBanks();
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, linkBanks)
                                .commit();



                    } else {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.                map.put("key", "add_account");
                map.put("key", " add_account");
                map.put("cardnum", Scardnum);
                map.put("cvv", Scvv);
                map.put("pin", Spin);
                map.put("balance", Sbal);
                map.put("uid", uid);


                return map;
            }
        };
        queue.add(request);
    }

    public void getACcountDetails() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                // Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();

                try {
                    if (!response.trim().equals("failed")) {

                        String data[] = response.trim().split("#");

                        cardno.setText(data[0]);
                        cardcvv.setText(data[1]);
                        cardpin.setText(data[2]);
                        cardbal.setText(data[3]);

                        cardno.setFocusableInTouchMode(false);
                        cardcvv.setFocusableInTouchMode(false);
                        cardpin.setFocusableInTouchMode(false);
                        btn.setText("Update Balance");

                    } else {
                        //Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
                        cardno.setFocusableInTouchMode(true);
                        cardcvv.setFocusableInTouchMode(true);
                        cardpin.setFocusableInTouchMode(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.                map.put("key", "add_account");
                map.put("key", "getACcountDetails");
                map.put("uid", uid);


                return map;
            }
        };
        queue.add(request);
    }



}
