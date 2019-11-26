package com.example.anticam.User;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anticam.Common.Base64;
import com.example.anticam.Common.Utility;
import com.example.anticam.PermissionController;
import com.example.anticam.R;
import com.example.anticam.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpotDetails extends Fragment {

    String CID;
    ImageView image;
    TextView name,district,place,description,topthings,time,contact,fee,restrict,holiday;
    Dialog dialog;
    Button btngetTicket;
    String PRICE,SID,DATE,UID,BALANCE,ACNO,NAME,TYPE="card",UP_BALANCE;

    public SpotDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_spot_details, container, false);

        btngetTicket= view.findViewById(R.id.spot_details_btngetpass);
        name= view.findViewById(R.id.spot_details_name);
        district= view.findViewById(R.id.spot_details_district);
        place= view.findViewById(R.id.spot_details_place);
        description= view.findViewById(R.id.spot_details_description);
        topthings= view.findViewById(R.id.spot_details_topthings);
        time= view.findViewById(R.id.spot_details_inout_time);
        contact= view.findViewById(R.id.spot_details_contact);
        fee= view.findViewById(R.id.spot_details_fee);
        image= view.findViewById(R.id.spot_details_image);
        restrict= view.findViewById(R.id.spot_details_restrict);
        holiday= view.findViewById(R.id.spot_details_holiday);



        btngetTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });



        getBookingCcountDetails();

        Bundle bundle = this.getArguments();

        if(bundle != null){
            CID=bundle.get("cid").toString();

           // Toast.makeText(getContext(), ""+bundle.get("district").toString(), Toast.LENGTH_SHORT).show();
            SID=bundle.getString("cid").toString().trim();
            name.setText(bundle.getString("name").toString());
            district.setText(bundle.getString("district").toString());
            place.setText(bundle.get("place").toString());
            description.setText(bundle.get("description").toString());
            topthings.setText(bundle.get("topthings").toString());
            time.setText("Time :"+bundle.get("in").toString()+" - "+bundle.get("out").toString());
            contact.setText(bundle.get("contact").toString());
            fee.setText(bundle.get("fee").toString());
            holiday.setText(bundle.get("holiday").toString());

            PRICE=bundle.get("fee").toString().trim();

            String cam=bundle.get("Cam").toString().trim();
            if(cam.equals("yes")){
                restrict.setText("Cam restricted");
            }else{
                restrict.setText("No restriction");
            }



            image.setScaleType(ImageView.ScaleType.FIT_XY);
            try {
                byte[] imageAsBytes = Base64.decode(bundle.get("image").toString().getBytes());

                image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            } catch (IOException e) {

                e.printStackTrace();
            }

        }



        return view;
    }



    protected void showCustomDialog() {
        // TODO Auto-generated method stub
        // final Dialog dialog = new Dialog(User_View_Recipie_Details.this);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_book_ticket_dialog);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        final Button btnbook = (Button) dialog.findViewById(R.id.cus_dlg_book_ticket_btnbook);
        final EditText name = (EditText) dialog.findViewById(R.id.cus_dlg_book_ticket_name);
        final EditText acno = (EditText) dialog.findViewById(R.id.cus_dlg_book_ticket_ac_no);
        final EditText price = (EditText) dialog.findViewById(R.id.cus_dlg_book_ticket_price);
        final Switch cashon = (Switch) dialog.findViewById(R.id.cus_dlg_book_ticket_cashon);


        name.setText(NAME);
        acno.setText(ACNO);
        price.setText("Amount  : "+ PRICE);

        cashon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    TYPE="cashon";
                    name.setVisibility(View.GONE);
                    acno.setVisibility(View.GONE);

                }else{
                    TYPE="card";
                    name.setVisibility(View.VISIBLE);
                    acno.setVisibility(View.VISIBLE);
                }

            }
        });
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UP_BALANCE=""+(Integer.parseInt(BALANCE)-Integer.parseInt(PRICE));
                Toast.makeText(getContext(), "UP_BALANCE"+UP_BALANCE, Toast.LENGTH_SHORT).show();

                if(PRICE.isEmpty()){
                    price.requestFocus();
                    price.setError("Amount");
                }else if(Integer.parseInt(PRICE)>Integer.parseInt(BALANCE)){
                    Toast.makeText(getContext(), "you don't have enough money available in your account..\n Your Balance  : "+BALANCE, Toast.LENGTH_LONG).show();
                }else{
                    bookingUpdate();
                }



            }
        });


        dialog.show();
    }


    public void getBookingCcountDetails() {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {


                    String data[]=response.trim().split("#");
                    NAME=data[0];
                    ACNO=data[1];
                    BALANCE=data[3];



                } else {
                    LinkBanks linkBanks = new LinkBanks();


                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, linkBanks)
                            .commit();
                    Toast.makeText(getContext(), "noval..!", Toast.LENGTH_LONG).show();
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
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                 UID = prefs.getString("logid", "No logid");//"No name defined" is the default value.
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "getBookingCcountDetails");
                map.put("uid",UID);


                return map;
            }
        };
        queue.add(request);
    }

    public void bookingUpdate() {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {


                    Toast.makeText(getContext(), "booking succesfull..!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(),UserHome.class));


                } else {
                    Toast.makeText(getContext(), "booking failed..!", Toast.LENGTH_SHORT).show();
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
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "bookingUpdate");
                map.put("uid",uid);
                map.put("sid",SID);
                map.put("price",PRICE);
                map.put("type",TYPE);
                map.put("up_balance",UP_BALANCE);


                return map;
            }
        };
        queue.add(request);
    }

}
