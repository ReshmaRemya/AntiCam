package com.example.anticam.Common;


import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anticam.R;

import java.io.IOException;
import java.util.ArrayList;


public class BookingsAdapter extends ArrayAdapter<LocationPojo> {
    Activity context;
    ArrayList<LocationPojo> rest_List;

    public BookingsAdapter(Activity context, ArrayList<LocationPojo> rest_List) {
        super(context, R.layout.custom_view_booking, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_view_booking, null, true);



        TextView loc_id = (TextView) view.findViewById(R.id.cust_bookings_id);
        TextView loc_name = (TextView) view.findViewById(R.id.cust_bookings_name);
        TextView loc_ticketno = (TextView) view.findViewById(R.id.cust_bookings_ticketno);
        TextView loc_price = (TextView) view.findViewById(R.id.cust_bookings_price);
        TextView loc_description = (TextView) view.findViewById(R.id.cust_bookings_description);

//        const_img.getLayoutParams().height=100;
//        const_img.getLayoutParams().width=100;
//        const_img.setScaleType(ImageView.ScaleType.FIT_XY);
//
        ImageView loc_image = (ImageView) view.findViewById(R.id.cust_bookings_img);
        loc_id.setText(rest_List.get(position).getLid());
        loc_name.setText(rest_List.get(position).getName());
        loc_ticketno.setText(rest_List.get(position).getBooking_number());
        loc_description.setText(rest_List.get(position).getDescription());
        loc_price.setText("Rs  : "+rest_List.get(position).getBooking_price());
        String base = rest_List.get(position).getImage();

        try {
            byte[] imageAsBytes = Base64.decode(base.getBytes());

            loc_image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
//
            // const_img.setBackground(new BitmapDrawable(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)));
        } catch (IOException e) {

            e.printStackTrace();
        }


        return view;
    }


}
