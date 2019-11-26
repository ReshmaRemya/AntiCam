package com.example.anticam.User;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.anticam.Common.DistrictPojo;
import com.example.anticam.Common.ImageAdapter;
import com.example.anticam.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_ByDistrict extends Fragment {


    GridView simpleList;
    ArrayList birdList=new ArrayList<>();


    String[] Districts = {"Thrissur", "Ernamkulam", "Kollam", "Palakkad", "Malappuram", "Trivandrum", "Kannur",
            "Calicut", "Kasargod", "Vayanad", "Idukki", "Pattanamthitta", "Kottayam", "Alappuzha"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search__by_district, container, false);


        simpleList = view.findViewById(R.id.grid_view);
        birdList.add(new DistrictPojo("Thrissur",R.drawable.ic_thrissur));
        birdList.add(new DistrictPojo("Ernakulam",R.drawable.ic_ernakulam));
        birdList.add(new DistrictPojo("Kollam",R.drawable.ic_kollam));
        birdList.add(new DistrictPojo("Palakkad",R.drawable.ic_palakkad));
        birdList.add(new DistrictPojo("Malappuram",R.drawable.ic_malappuram));
        birdList.add(new DistrictPojo("Trivandrum",R.drawable.ic_trivandram));
        birdList.add(new DistrictPojo("Kannur",R.drawable.ic_kannur));
        birdList.add(new DistrictPojo("Calicut",R.drawable.ic_kozhikode));
        birdList.add(new DistrictPojo("Kasargod",R.drawable.ic_kasaragod));
        birdList.add(new DistrictPojo("Wayanad",R.drawable.ic_wayanad));
        birdList.add(new DistrictPojo("Idukki",R.drawable.ic_idukki));
        birdList.add(new DistrictPojo("Pattanamthitta",R.drawable.ic_pathanamthitta));
        birdList.add(new DistrictPojo("Kottayam",R.drawable.ic_kottayam));
        birdList.add(new DistrictPojo("Alappuzha",R.drawable.ic_alappuzha));

        ImageAdapter myAdapter=new ImageAdapter(getContext(),R.layout.grid_layout,birdList);
        simpleList.setAdapter(myAdapter);


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {



                Bundle bundle = new Bundle();
                bundle.putString("DistVal",Districts[pos]); // Put anything what you want

                DistBy_SpotList DistBy_SpotList = new DistBy_SpotList();
                DistBy_SpotList.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, DistBy_SpotList)
                        .commit();
            }
        });



        return view;
    }

}
