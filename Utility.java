package com.example.anticam.Common;

public class Utility {
    public static String lat="";
    public static String log="";
    public static String cid="";
    public static String phone="";
    public static String url="http://192.168.43.246:8080/Anti_Cam/Android/ServerController.jsp";
//    public static String url="http://192.168.43.246:8080/Anti_Cam/Android/ServerController.jsp";



    public static double  LOCK_DISTANCE=0.0;



    public static double distanceFinder(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}
