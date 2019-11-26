package com.example.anticam.User;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anticam.Common.LocationAdapter;
import com.example.anticam.Common.LocationPojo;
import com.example.anticam.Common.Utility;
import com.example.anticam.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistBy_SpotList extends Fragment {

    ArrayList<LocationPojo> arrayList;
    LocationAdapter adpater;
    ListView location_list;
    SharedPreferences sp;
    SearchView search;
    String DistVal;
    public DistBy_SpotList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dist_by__spot_list, container, false);


        Bundle bundle = this.getArguments();

        if(bundle != null){
            DistVal=bundle.get("DistVal").toString();

        }

        location_list=view.findViewById(R.id.spot_by_dist__listview);

        arrayList=new ArrayList<LocationPojo>();
        adpater=new LocationAdapter((Activity) getContext(), arrayList);

        location_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putString("cid",arrayList.get(position).getLid());
                bundle.putString("name",arrayList.get(position).getName());
                bundle.putString("district",arrayList.get(position).getDistrict());
                bundle.putString("place",arrayList.get(position).getPlace());
                bundle.putString("description",arrayList.get(position).getDescription());
                bundle.putString("topthings",arrayList.get(position).getTop_things());
                bundle.putString("in",arrayList.get(position).getIn());
                bundle.putString("out",arrayList.get(position).getOut());
                bundle.putString("contact",arrayList.get(position).getContact());
                bundle.putString("fee",arrayList.get(position).getEntry_fee());
                bundle.putString("image",arrayList.get(position).getImage());
                bundle.putString("Cam",arrayList.get(position).getCam_restrict());
                bundle.putString("holiday",arrayList.get(position).getHoliday());

                SpotDetails spotDetails = new SpotDetails();
                spotDetails.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, spotDetails)
                        .commit();

            }
        });




        if(!DistVal.trim().isEmpty()){
            getDistSpotLocations();

        }else{
            Toast.makeText(getContext(), "No Spot Location Found", Toast.LENGTH_SHORT).show();
        }






        return view;
    }


    public void getDistSpotLocations()
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
                        bean.setHoliday(ListBean.getHoliday());

                        arrayList.add(bean);
                        location_list.setAdapter(adpater);
                    }



                }
                else
                {
                    Toast.makeText(getContext(), "No location found", Toast.LENGTH_LONG).show();
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

                map.put("key","getDistSpotLocations");
                map.put("DistVal",DistVal);

                return map;
            }
        };
        queue.add(request);
    }

}
