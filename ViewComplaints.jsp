
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
                        <h3>Complaints</h3>
                        <span class="lft-bar-fea"> </span>
                        <span class="rit-bar-fea"> </span>
                    </div>
                    <div class="features-bottom">
                        <!--edit start ...-->

                  <center>
       <div class="advice-lidt-bottom wow bounceInRight" data-wow-delay="0.3s">
           
           
           
         <br><br> <br><br>
        
         <table >
             <tr><th>Name </th><th>Email </th><th>Phone </th><th>Subject </th><th>Complaint </th><th>Reply</th></tr>
                <%
                    DbConnection con= new DbConnection();
                    String qry = "SELECT u.`uid`, u.`name`,u.`email`,u.`phone`,c.`subject`,c.`compliant`,c.cid  FROM  `user_registration` u,`user_compliant` c WHERE u.`uid`=c.`uid`";
        System.out.println("qry=" + qry);
        Iterator it = con.getData(qry).iterator();
       
            while (it.hasNext()) {
                Vector v = (Vector) it.next(); 
                %>
                <tr>
                    <td><%=v.get(1)%></td>  <td><%=v.get(2)%></td>  <td><%=v.get(3)%></td>  <td><%=v.get(4)%></td>  <td><%=v.get(5)%></td><td><a href="SendComplaintReply.jsp?cid=<%=v.get(6)%>"><button>reply</button></a></td>
                </tr>
                
              <%  
            }
                    %>
            
            </table>
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