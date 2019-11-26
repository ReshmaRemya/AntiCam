package com.example.anticam.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anticam.R;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter {

        ArrayList<DistrictPojo> birdList;

        public ImageAdapter(Context context, int textViewResourceId, ArrayList objects) {
            super(context, textViewResourceId, objects);
            birdList = objects;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.grid_layout, null);
            TextView textView = v.findViewById(R.id.grid_item_label);
            ImageView imageView =  v.findViewById(R.id.grid_item_image);
            textView.setText(birdList.get(position).getDist_name());
            imageView.setImageResource(birdList.get(position).getDist_image());
            return v;

        }

    }
