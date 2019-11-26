
<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%@page import="dbConnection.DbConnection"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Anti Cam</title>
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="../js/jquery-1.11.0.min.js"></script>
        <!-- Custom Theme files -->
        <link href="../css/style.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="../css/style1.css" rel="stylesheet" type="text/css" media="all" />	
        <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Tour Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--Google Fonts-->
        <link href='//fonts.googleapis.com/css?family=Lato:400,300,700,900' rel='stylesheet' type='text/css'>
        <!-- //end-smoth-scrolling -->
        <script src="../js/responsiveslides.min.js"></script>
        <script>
            // You can also use "$(window).load(function() {"
            $(function () {
                $("#slider2").responsiveSlides({
                    auto: true,
                    pager: true,
                    speed: 300,
                    namespace: "callbacks",
                });
            });
        </script>
        <!-- requried-jsfiles-for owl -->
        <link href="../css/owl.carousel.css" rel="stylesheet">
        <script src="../js/owl.carousel.js"></script>
        <script>
            $(document).ready(function () {
                $("#owl-demo").owlCarousel({
                    items: 3,
                    lazyLoad: true,
                    autoPlay: false,
                    pagination: true,
                });
            });
        </script>

        <!-- //requried-jsfiles-for owl -->
        <!-- animated-css -->
        <link href="../css/animate.css" rel="stylesheet" type="text/css" media="all">
        <script src="../js/wow.min.js"></script>
        <script>
            new WOW().init();
        </script>
        <!-- animated-css -->

        <script>


            //............


// Below Function Executes On Form Submit
            function checkCon() {
// Storing Field Values In Variables
//alert("hhh");
                var name = document.getElementById("name").value;
                var place = document.getElementById("place").value;
                var contact = document.getElementById("contact").value;
                var camres = document.getElementById("restriction").value;
                var top = document.getElementById("top").value;
                var entry = document.getElementById("fee").value;
                var latt = document.getElementById("latt").value;
                var long = document.getElementById("long").value;
                var timein = document.getElementById("tin").value;
                var timeout = document.getElementById("tout").value;
                var holiday = document.getElementById("hdays").value;

// Conditions
                //alert(contact);
                if (name != '' && place != '' && contact != '' && camres != '' && top != '' && entry != '' && latt != '' && long != '' && timein != '' && timeout != '' && holiday != '' ) {
//                   alert("inside if");

                    if (name.length <= 3) {
                        alert("The Name . must be at least 4 digit!");
                        return false;
                    } else if (contact.length != 10) {
                        alert("The Contact No. must be at least 10 digit long!");
                        return false;
                    }
                    {
                        // alert("The Contact No. must be at least 10 digit long!");
                        return true;
                    }
//                       
                } else {
                    alert("All fields are required.....!");
                    return false;
                }
            }



        </script>
    </head>
    <body>
        <!--header start here-->




        <%@include file="Header.jsp" %>


        <!--header end here-->


        <!--features strat here-->
        <div class="features">
            <div class="container">
                <div class="features-main">
                    <div class="features-top">
                        <h3>Add Locations</h3>
                        <span class="lft-bar-fea"> </span>
                        <span class="rit-bar-fea"> </span>
                    </div>
                    <div class="features-bottom">
                        <!--edit start ...-->

                        <center>
                            <div class="advice-lidt-bottom wow bounceInRight" data-wow-delay="0.3s">


                                <!--onsubmit="return checkCon()" -->
                                <form onsubmit="return checkCon()"  action="../Process/AddLocationAction.jsp" method="post" enctype="multipart/form-data">

                                    Name <input type="text" name="name" id="name" class="form-control_edited"   required="">
                                    Description <textarea name="description" class="form-control_edited"  required=""></textarea>
                                    District <select name="district" class="form-control_edited"  required="">
                                        <%
                                            DbConnection con = new DbConnection();
                                            String qry = "SELECT  *FROM `district`";
                                            System.out.println("qry=" + qry);
                                            Iterator it = con.getData(qry).iterator();

                                            while (it.hasNext()) {
                                                Vector v = (Vector) it.next();
                                        %>
                                        <option value="<%=v.get(1)%>"><%=v.get(1)%></option>
                                        <%
                                            }
                                        %>              
                                    </select>

                                    place <input type="text" id="place" name="place" class="form-control_edited"  required="">
                                    Cam Restriction   <input type="radio" id="restriction" name="restriction" class="" value="yes" required="">Yes
                                    <input type="radio" name="restriction" value="No" required="">No<br>
                                    Top Things  <textarea name="top_things" id="top" class="form-control_edited" required=""></textarea>
                                    Entry Fee <input type="number" name="fee" id="fee" class="form-control_edited" required="">
                                    Contact <input type="number" id="contact" name="contact" pattern="^\d{10}$" class="form-control_edited" required=""><br>
                                    <button onclick="window.open('https://www.google.com/maps/');">
                                        <code>Get Latitude & Longitude</code>
                                    </button><br>
                                    Latitude <input type="text" name="lattitude" id="latt" class="form-control_edited" required="" s>
                                    Longitude <input type="text" name="longittude" id="long" class="form-control_edited" required="">
                                    (Time)  In<input type="time" name="intime" id="tin" class="form-control_edited" required=""> 
                                    Out <input type="time" name="outtime" id="tout" class="form-control_edited" required="">
                                    Holidays <input type="text" name="holiday" id="hdays" class="form-control_edited" required="">
                                    Image  <input type="file" name="image" class="form-control_edited" required="">
                                    <input type="submit" class="btn_submit_edited"  value="Add Location " id="btn">

                                </form>
                                <br><br>

                            </div>
                        </center>


                        <!--edit start end...-->

                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>
        </div>
        <!--features end here-->

        <!--footer start here-->

        <!--footer end here-->
        <div class="copy-right wow bounceInLeft" data-wow-delay="0.3s">
            <p>  2019 Anti Cam. All rights reserved | Lcc edu..</p>
        </div>	
    </body>
</html>