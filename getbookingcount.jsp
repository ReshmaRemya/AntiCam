<%-- 
    Document   : getbookingcount
    Created on : 19 Nov, 2019, 5:21:58 PM
    Author     : admin
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javafx.util.converter.LocalDateStringConverter"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>





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

        <style>
            table {
                border-collapse: collapse;
                width: 80%;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            tr:hover {background-color:#f5f5f5;}
        </style>

        <!-- //requried-jsfiles-for owl -->
        <!-- animated-css -->
        <link href="../css/animate.css" rel="stylesheet" type="text/css" media="all">
        <script src="../js/wow.min.js"></script>
        <script>
            new WOW().init();
        </script>
        <!-- animated-css -->
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
                        <h3>Booking Count</h3>
                        <span class="lft-bar-fea"> </span>
                        <span class="rit-bar-fea"> </span>
                    </div>
                    <div class="features-bottom">
                        <!--edit start ...-->

                        <center>
                            <div class="advice-lidt-bottom wow bounceInRight" data-wow-delay="0.3s">




                                <%

                                    String frdate, todate;
                                    frdate = request.getParameter("fromdate").toString();
                                    todate = request.getParameter("todate").toString();

                                    String fr[] = frdate.split("-");
                                    String tr[] = todate.split("-");
                                    String frdate1 = fr[2] + "-" + fr[1] + "-" + fr[0];
                                    String todate1 = tr[2] + "-" + tr[1] + "-" + tr[0];

                                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");

                                    //    Date fr1=df.format(frdate);
                                    System.out.print(frdate + " " + todate);
                                    LocalDate d1 = LocalDate.parse(frdate);

                                    LocalDate d2 = LocalDate.parse(todate);

                                    String qry = "SELECT SUM(price),COUNT(ticketno) FROM `bookings` WHERE DATE BETWEEN '" + d1 + "' AND '" + d2 + "'";
                                    System.out.println("qry : " + qry);
                                    dbConnection.DbConnection ob = new dbConnection.DbConnection();
                                    Iterator it = ob.getData(qry).iterator();
                                    if (it.hasNext()) {

                                        Vector v = (Vector) it.next();
                                        if (!v.get(1).equals("0")) {
                                %>

                                <table id="cstable">
                                    <tr>
                                        <th>Number of Tickets </th><th>Total Cash </th>
                                    </tr>

                                    <tr>
                                        <td><%=v.get(1)%></td> 
                                        <td><%=v.get(0)%></td>  
                                    </tr><%

                                    } else {

                                    %>

                                    <h4>No Data Available on these days</h4>
                                    <%                                            }
                                        }
                                    %>
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
        

    </body>
</html>