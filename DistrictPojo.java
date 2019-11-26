package com.example.anticam.Common;

public class DistrictPojo {

    String dist_name;
      int dist_image;

    public DistrictPojo(String dist_name,int dist_image)
    {
        this.dist_image=dist_image;
        this.dist_name=dist_name;
    }
    public int getDist_image() {
        return dist_image;
    }

    public void setDist_image(int dist_image) {
        this.dist_image = dist_image;
    }

    public String getDist_name() {
        return dist_name;
    }

    public void setDist_name(String dist_name) {
        this.dist_name = dist_name;
    }
}
