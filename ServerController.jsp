
<%@page import="Utility.GenarateTickets"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="Utility.LocationPojo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%@page import="dbConnection.DbConnection"%>
<%
    DbConnection con = new DbConnection();

    String key = request.getParameter("key").trim();
    System.out.println("key  = " + key);

    if (key.equals("login")) {
        String info = "";
        String qry = "select * from `login` where username='" + request.getParameter("username") + "'and password='" + request.getParameter("password") + "' ";
        System.out.println("qry=" + qry);

        Iterator it = con.getData(qry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            info = v.get(1) + ":" + v.get(4);
            out.print(info);
        } else {
            System.out.println("else id=" + info);
            out.print("failed");
        }
    }

    if (key.equals("reg_user")) {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String aadhar = request.getParameter("aadhar");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String qry = "INSERT INTO `user_registration`(`name`,`gender`,`aadhaar`,`phone`,`email`,`password`) VALUES ('" + name + "','" + gender + "','" + aadhar + "','" + phone + "','" + email + "','" + password + "') ";
        String qry1 = "insert into login (uid,username,password,type)values((select max(uid)from user_registration),'" + email + "','" + password + "','user')";
        if (con.putData(qry) > 0) {

            if (con.putData(qry1) > 0) {
                out.print("successful");
            }

        } else {
            out.print("failed");
        }
    }

//addFeedBack...
    if (key.equals("addFeedBack")) {
        String sub = request.getParameter("subject");
        String desc = request.getParameter("description");
        String rating = request.getParameter("rating");
        String uid = request.getParameter("uid");

        String qry = "INSERT INTO `feedback` (`uid`,`subject`,`description`,`rating`,`status` ) VALUES ('" + uid + "','" + sub + "','" + desc + "','" + rating + "','0') ";
        System.out.println(qry);
        if (con.putData(qry) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }

//addComplaint...
    if (key.equals("addComplaint")) {
        String sub = request.getParameter("subject");
        String complaint = request.getParameter("complaint");
        String uid = request.getParameter("uid");

        String qry = "INSERT INTO `user_compliant` (`uid`,`subject`,`compliant`) VALUES ('" + uid + "','" + sub + "','" + complaint + "') ";
        System.out.println(qry);
        if (con.putData(qry) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }

//getSpotLocations
    if (key.equals("getSpotLocations")) {
        String result = "";

        List<LocationPojo> allInfos = new ArrayList<LocationPojo>();
        String str = "SELECT * FROM `location_details` ";
        System.out.println(str);
        Vector v = con.getData(str);
        Iterator itr = v.iterator();
        while (itr.hasNext()) {
            Vector vv = (Vector) itr.next();
            LocationPojo bean = new LocationPojo();
            bean.setLid(vv.get(0).toString());
            bean.setName(vv.get(1).toString());
            bean.setDescription(vv.get(2).toString());
            bean.setDistrict(vv.get(3).toString());
            bean.setPlace(vv.get(4).toString());
            bean.setCam_restrict(vv.get(5).toString());
            bean.setTop_things(vv.get(6).toString());
            bean.setEntry_fee(vv.get(7).toString());
            bean.setContact(vv.get(8).toString());
            bean.setLattitude(vv.get(9).toString());
            bean.setLongittude(vv.get(10).toString());
            bean.setIn(vv.get(11).toString());
            bean.setOut(vv.get(12).toString());
            bean.setImage(vv.get(14).toString());
            bean.setHoliday(vv.get(15).toString());
            allInfos.add(bean);
        }
        Gson gson = new Gson();

        String infoall = gson.toJson(allInfos);
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
        }
        System.out.println(infoall);
    }

//getSearchLocations
    if (key.equals("getSearchLocations")) {
        String result = "";

        String Searchval = request.getParameter("Searchval");

        List<LocationPojo> allInfos = new ArrayList<LocationPojo>();
        String str = "SELECT * FROM `location_details` where  `name` LIKE  '" + Searchval + "%'";
        System.out.println(str);
        Vector v = con.getData(str);
        Iterator itr = v.iterator();
        while (itr.hasNext()) {
            Vector vv = (Vector) itr.next();
            LocationPojo bean = new LocationPojo();
            bean.setLid(vv.get(0).toString());
            bean.setName(vv.get(1).toString());
            bean.setDescription(vv.get(2).toString());
            bean.setDistrict(vv.get(3).toString());
            bean.setPlace(vv.get(4).toString());
            bean.setCam_restrict(vv.get(5).toString());
            bean.setTop_things(vv.get(6).toString());
            bean.setEntry_fee(vv.get(7).toString());
            bean.setContact(vv.get(8).toString());
            bean.setLattitude(vv.get(9).toString());
            bean.setLongittude(vv.get(10).toString());
            bean.setIn(vv.get(11).toString());
            bean.setOut(vv.get(12).toString());
            bean.setImage(vv.get(14).toString());
            bean.setHoliday(vv.get(15).toString());
            allInfos.add(bean);
        }
        Gson gson = new Gson();

        String infoall = gson.toJson(allInfos);
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
        }
        System.out.println(infoall);
    }

//getDistSpotLocations
    if (key.equals("getDistSpotLocations")) {
        String result = "";

        String DistVal = request.getParameter("DistVal");

        List<LocationPojo> allInfos = new ArrayList<LocationPojo>();
        String str = "SELECT * FROM `location_details` where  `district` LIKE  '" + DistVal + "%'";
        System.out.println(str);
        Vector v = con.getData(str);
        Iterator itr = v.iterator();
        while (itr.hasNext()) {
            Vector vv = (Vector) itr.next();
            LocationPojo bean = new LocationPojo();
            bean.setLid(vv.get(0).toString());
            bean.setName(vv.get(1).toString());
            bean.setDescription(vv.get(2).toString());
            bean.setDistrict(vv.get(3).toString());
            bean.setPlace(vv.get(4).toString());
            bean.setCam_restrict(vv.get(5).toString());
            bean.setTop_things(vv.get(6).toString());
            bean.setEntry_fee(vv.get(7).toString());
            bean.setContact(vv.get(8).toString());
            bean.setLattitude(vv.get(9).toString());
            bean.setLongittude(vv.get(10).toString());
            bean.setIn(vv.get(11).toString());
            bean.setOut(vv.get(12).toString());
            bean.setImage(vv.get(14).toString());
            bean.setHoliday(vv.get(15).toString());
            allInfos.add(bean);
        }
        Gson gson = new Gson();

        String infoall = gson.toJson(allInfos);
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
        }
        System.out.println(infoall);
    }

//getMapSpotLocations
    if (key.equals("getMapSpotLocations")) {

        String result = "";

        List<LocationPojo> allInfos = new ArrayList<LocationPojo>();
        String str = "SELECT * FROM `location_details` ";
        System.out.println(str);
        Vector v = con.getData(str);
        Iterator itr = v.iterator();
        while (itr.hasNext()) {
            Vector vv = (Vector) itr.next();
            LocationPojo bean = new LocationPojo();
            bean.setLid(vv.get(0).toString());
            bean.setName(vv.get(1).toString());
            bean.setDescription(vv.get(2).toString());
            bean.setDistrict(vv.get(3).toString());
            bean.setPlace(vv.get(4).toString());
            bean.setCam_restrict(vv.get(5).toString());
            bean.setTop_things(vv.get(6).toString());
            bean.setEntry_fee(vv.get(7).toString());
            bean.setContact(vv.get(8).toString());
            bean.setLattitude(vv.get(9).toString());
            bean.setLongittude(vv.get(10).toString());
            bean.setIn(vv.get(11).toString());
            bean.setOut(vv.get(12).toString());
            bean.setImage(vv.get(14).toString());
            bean.setHoliday(vv.get(15).toString());

            allInfos.add(bean);
        }
        Gson gson = new Gson();

        String infoall = gson.toJson(allInfos);
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
        }
        System.out.println(infoall);
    }

// add_account
    if (key.equals("add_account")) {
        String result = "";
        String uid = request.getParameter("uid").toString();
        String pin = request.getParameter("pin").toString();
        String accno = request.getParameter("cardnum").toString();
        String cvv = request.getParameter("cvv").toString();
        String balance = request.getParameter("balance").toString();

        System.out.println(uid + " " + pin + " " + accno + " " + cvv + " " + balance);

        String checkqry = "SELECT * FROM `bank` WHERE `uid`='" + uid + "'";

        String str = " INSERT INTO `bank` (`card_no`,`cvv_no`,`pin`,`balance`,`status`,`uid`) "
                + "VALUES ('" + accno + "','" + cvv + "','" + pin + "','" + balance + "','1','" + uid + "')";

        System.out.println(str);

        Iterator itr = con.getData(checkqry).iterator();
        if (itr.hasNext()) {

            System.out.println(checkqry);
            out.println("accountexists");

            String str3 = "UPDATE `bank` SET `balance`='" + balance + "', `card_no`='" + accno + "' , `cvv_no`='" + cvv + "' , `pin`='" + pin + "' WHERE uid='" + uid + "'";
            if (con.putData(str3) > 0) {
                out.println("success");
            } else {
                out.println("failed");
            }

        } else {
            if (con.putData(str) > 0) {

                out.print("success");
                System.out.println("success");
            } else {

                out.println("failed");
            }
        }

    }

//  getACcountDetails  
    if (key.equals("getACcountDetails")) {
        String result = "";
        String uid = request.getParameter("uid").toString();
        String qry = "SELECT * FROM `bank` WHERE `uid`='" + uid + "'";
        System.out.println("qry  " + qry);
        Iterator itr = con.getData(qry).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();

            result = v.get(2) + "#" + v.get(3) + "#" + v.get(4) + "#" + v.get(5);
            out.println(result);
        } else {
            out.println("failed");
        }

    }

//   getBookingCcountDetails
    if (key.equals("getBookingCcountDetails")) {
        String result = "";
        String uid = request.getParameter("uid").toString();
        String qry = "SELECT u.`name`,b.`card_no`,b.`pin`,b.`balance`  FROM `user_registration` u,`bank` b  WHERE u.`uid`=b.`uid` AND u.uid='" + uid + "'";
        System.out.println("qry  " + qry);
        Iterator itr = con.getData(qry).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();

            result = v.get(0) + "#" + v.get(1) + "#" + v.get(2) + "#" + v.get(3);
            out.println(result);
        } else {
            out.println("failed");
        }

    }

// bookingUpdate...
    if (key.equals("bookingUpdate")) {

        String uid = request.getParameter("uid");
        String sid = request.getParameter("sid");
        String price = request.getParameter("price");
        String up_balance = request.getParameter("up_balance");
        String type = request.getParameter("type");
        String ticketno = sid + "-" + new String(GenarateTickets.getticketNumber(4));

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
        String today = format.format(date);

        String qry = "INSERT INTO `bookings`(`uid`,`sid`,`ticketno`,`price`,`date`,`type`,`status`) VALUES('" + uid + "','" + sid + "','" + ticketno + "','" + price + "','" + today + "','" + type + "','0')";
        System.out.println(qry);
        if (con.putData(qry) > 0) {

            if (type.trim().equals("card")) {

                String qry1 = "UPDATE `bank` SET `balance`='" + up_balance + "' WHERE `uid`='" + uid + "'";
                System.out.println(qry);
                if (con.putData(qry1) > 0) {
                    out.print("successful");
                }

            } else {
                out.print("successful");
            }

        } else {
            out.print("failed");
        }
    }

//  getBookings
    if (key.equals("getBookings")) {

        String result = "";
        String uid = request.getParameter("uid");
        List<LocationPojo> allInfos = new ArrayList<LocationPojo>();
        String str = "SELECT * FROM `location_details` l,`bookings` b,`user_registration` u WHERE l.`lid`=b.`sid` AND b.`uid`=u.`uid` AND u.uid='" + uid + "'";
        System.out.println(str);
        Vector v = con.getData(str);
        Iterator itr = v.iterator();
        while (itr.hasNext()) {
            Vector vv = (Vector) itr.next();
            LocationPojo bean = new LocationPojo();
            bean.setLid(vv.get(0).toString());
            bean.setName(vv.get(1).toString());
            bean.setDescription(vv.get(2).toString());
            bean.setDistrict(vv.get(3).toString());
            bean.setPlace(vv.get(4).toString());
            bean.setCam_restrict(vv.get(5).toString());
            bean.setTop_things(vv.get(6).toString());
            bean.setEntry_fee(vv.get(7).toString());
            bean.setContact(vv.get(8).toString());
            bean.setLattitude(vv.get(9).toString());
            bean.setLongittude(vv.get(10).toString());
            bean.setIn(vv.get(11).toString());
            bean.setOut(vv.get(12).toString());
            bean.setImage(vv.get(14).toString());
            bean.setBooking_number(vv.get(18).toString());
            bean.setBooking_price(vv.get(19).toString());
            bean.setBooking_date(vv.get(20).toString());
            bean.setBooking_type(vv.get(21).toString());

            allInfos.add(bean);
        }
        Gson gson = new Gson();

        String infoall = gson.toJson(allInfos);
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
        }
        System.out.println(infoall);
    }

//   getComplaintReply
    if (key.equals("getComplaintReply")) {
        String result = "";
        String uid = request.getParameter("uid").toString();
        String qry = "SELECT  u.`subject`,c.`complaint`,c.`reply` FROM `complaint_reply` c,`user_compliant` u WHERE c.`cid`=u.`cid` AND c.`uid`='" + uid + "'";
        System.out.println("qry  " + qry);
        Iterator itr = con.getData(qry).iterator();
        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();

                result = "SUBJECT        : " + v.get(0) + "\nCOMPLAINT  : " + v.get(1) + "\nREPLY             : " + v.get(2) + "#";

            }
            out.println(result);

        } else {
            out.println("failed");
        }

    }

// getDisableLatLong
    if (key.equals("getDisableLatLong")) {
        String result = "", latt = "", longg = "";

        String qry = "SELECT `lattitude`,`longittude` FROM `location_details` WHERE `cam_restrict`='yes'";
        System.out.println("qry  " + qry);
        Iterator itr = con.getData(qry).iterator();
        if (itr.hasNext()) {

            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                latt += v.get(0) + "#";
                longg += v.get(1) + "#";
            }

            result = latt + "&" + longg;

            System.out.println("result : " + result);
            out.println(result);
        } else {
            out.println("failed");
        }

    }
%>