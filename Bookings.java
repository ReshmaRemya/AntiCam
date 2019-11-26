package com.example.anticam.User;


import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.example.anticam.Common.BookingsAdapter;
import com.example.anticam.Common.LocationAdapter;
import com.example.anticam.Common.LocationPojo;
import com.example.anticam.Common.Utility;
import com.example.anticam.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Bookings extends Fragment {


    ArrayList<LocationPojo> arrayList;
    BookingsAdapter adpater;
    ListView location_list;
    String uid;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bookings, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
        uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.

        location_list=(ListView) view.findViewById(R.id.booking_bookinglis);

        arrayList=new ArrayList<LocationPojo>();

        adpater=new BookingsAdapter((Activity) getContext(), arrayList);



        getBookings();


        location_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String tiknum,name,image,time,type;
                tiknum=arrayList.get(i).getBooking_number();
                name=arrayList.get(i).getName();
                image=arrayList.get(i).getImage();
                type=arrayList.get(i).getBooking_type();
                time="Time : "+arrayList.get(i).getIn().trim()+"-"+arrayList.get(i).getOut().trim();
                showCustomDialog(tiknum,name,image,time,type);
            }
        });



        return view;
    }


    protected void showCustomDialog(String ...data) {
        // TODO Auto-generated method stub
        // final Dialog dialog = new Dialog(User_View_Recipie_Details.this);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_view_ticket_dialog);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        final TextView ticket_number = (TextView) dialog.findViewById(R.id.cus_dlg_view_ticket_number);
        final TextView ticket_name = (TextView) dialog.findViewById(R.id.cus_dlg_view_ticket_name);
        final TextView ticket_inout = (TextView) dialog.findViewById(R.id.cus_dlg_view_ticket_inout_time);
        final CircleImageView image = (CircleImageView) dialog.findViewById(R.id.cus_dlg_view_ticket_iamge);
        final ImageView type_image = (ImageView) dialog.findViewById(R.id.cus_dlg_view_ticket_type_image);



        if(data[4].trim().equals("card")){
            type_image.setImageResource(R.drawable.pain_logo);
        }else{
            type_image.setImageResource(R.drawable.notpaid_logo);
        }


        ticket_number.setText(data[0]);
        ticket_name.setText(data[1]);
        ticket_inout.setText(data[3]);

//        image.setScaleType(ImageView.ScaleType.fitXY);
        try {
            byte[] imageAsBytes = Base64.decode(data[2]);

            image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        } catch (IOException e) {

            e.printStackTrace();
        }





        dialog.show();
    }


    public void getBookings()
    {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {

                    Gson gson=new Gson();
                    LocationPojo[] beans=gson.fromJson(response, LocationPojo[].class);

                    for(LocationPojo ListBean:beans){
                        LocationPojo bean=new LocationPojo();

                        bean.setLid(ListBean.getLid());
                        bean.setName(ListBean.getName());
                        bean.setDescription(ListBean.getDescription());
                        bean.setEntry_fee(ListBean.getEntry_fee());
                        bean.setPlace(ListBean.getPlace());
                        bean.setTop_things(ListBean.getTop_things());
                        bean.setIn(ListBean.getIn());
                        bean.setOut(ListBean.getOut());
                        bean.setCam_restrict(ListBean.getCam_restrict());
                        bean.setContact(ListBean.getContact());
                        bean.setDistrict(ListBean.getDistrict());
                        bean.setImage(ListBean.getImage());
                        bean.setLattitude(ListBean.getLattitude());
                        bean.setLongittude(ListBean.getLongittude());
                        bean.setBooking_date(ListBean.getBooking_date());
                        bean.setBooking_price(ListBean.getBooking_price());

                        Log.d("setBooking_price",""+ListBean.getBooking_price());
                        bean.setBooking_number(ListBean.getBooking_number());
                        bean.setBooking_type(ListBean.getBooking_type());


                        arrayList.add(bean);
                        location_list.setAdapter(adpater);
                    }



                }
                else
                {
                  //  Toast.makeText(getActivity(), "No location found", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                Toast.makeText(getContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> map = new HashMap<String, String>();
                map.put("key","getBookings");
                map.put("uid",uid);
                return map;
            }
        };
        queue.add(request);
    }
}
