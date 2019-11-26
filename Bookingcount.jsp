
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
           
           
           
        <form action="getbookingcount.jsp" method="post" >
           
            From Date <input type="date" name="fromdate">&nbsp;
             To Date <input type="date" name="todate">
                 <input type="submit" class="btn_submit_edited" value="Get Booking Count" id="btn">
         
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
            <p> ©2019 Anti Cam. All Rights Reserved.| Govt. of Kerala</p>
        </div>	
    </body>
</html>