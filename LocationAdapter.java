package com.example.anticam.Common;


import android.app.Activity;
import android.graphics.BitmapFactory;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.anticam.R;




import java.io.IOException;
import java.util.ArrayList;


public class LocationAdapter extends ArrayAdapter<LocationPojo> {
    Activity context;
    ArrayList<LocationPojo> rest_List;

    public LocationAdapter(Activity context, ArrayList<LocationPojo> rest_List) {
        super(context, R.layout.custom_view_locations, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_view_locations, null, true);



        TextView loc_id = (TextView) view.findViewById(R.id.cust_loc_id);
        TextView loc_name = (TextView) view.findViewById(R.id.cust_loc_name);
        TextView loc_description = (TextView) view.findViewById(R.id.cust_loc_description);
        TextView loc_fee = (TextView) view.findViewById(R.id.cust_loc_fee);

//        const_img.getLayoutParams().height=100;
//        const_img.getLayoutParams().width=100;
//        const_img.setScaleType(ImageView.ScaleType.FIT_XY);
//
        ImageView loc_image = (ImageView) view.findViewById(R.id.cust_loc_img);
        loc_id.setText(rest_List.get(position).getLid());
        loc_name.setText(rest_List.get(position).getName());
        loc_description.setText(rest_List.get(position).getDescription());
        loc_fee.setText("Rs  : "+rest_List.get(position).getEntry_fee());
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
