package com.example.anticam.User;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anticam.Common.GPSTracker;
import com.example.anticam.Common.LocationPojo;
import com.example.anticam.Common.Utility;
import com.example.anticam.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Spot_NearMe extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match

    private GoogleMap mMap;
    GPSTracker gps;
    Double latitude,longitude;

    ArrayList<LocationPojo> arrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_spot__near_me, container, false);

        gps = new GPSTracker(getContext());
        arrayList=new ArrayList<LocationPojo>();
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();




        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        return  view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        mMap.addMarker(new MarkerOptions().position( new LatLng(latitude, longitude)).title("Me").snippet(""));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(18).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        getSpotLocatons();


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {


                int position=Integer.parseInt(marker.getId().replaceAll("[m]",""))-1;
                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();

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
    }


    public void getSpotLocatons() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {

                    Gson gson=new Gson();
                    LocationPojo[] beans=gson.fromJson(response, LocationPojo[].class);

                    for(LocationPojo ListBean:beans) {
                        LocationPojo bean = new LocationPojo();

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

                        Double  sel_lati=Double.parseDouble(ListBean.getLattitude());
                        Double  sel_longi=Double.parseDouble(ListBean.getLongittude());
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(sel_lati, sel_longi))
                                .title(ListBean.getName())
                                .snippet(ListBean.getDescription())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.shopicon)));

                    }


                } else {
                    Toast.makeText(getContext(), "Login failed..!", Toast.LENGTH_LONG).show();
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
                map.put("key", "getMapSpotLocations");

                return map;
            }
        };
        queue.add(request);
    }
}
