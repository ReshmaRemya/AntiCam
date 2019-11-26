
<!DOCTYPE HTML>
<html>
    <head>
        <title>Anti Cam</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery-1.11.0.min.js"></script>
        <!-- Custom Theme files -->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />	
        <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Tour Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--Google Fonts-->
        <link href='//fonts.googleapis.com/css?family=Lato:400,300,700,900' rel='stylesheet' type='text/css'>
        <!-- //end-smoth-scrolling -->
        <script src="js/responsiveslides.min.js"></script>
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
        <link href="css/owl.carousel.css" rel="stylesheet">
        <script src="js/owl.carousel.js"></script>
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
        <link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
        <script src="js/wow.min.js"></script>
        <script>
            new WOW().init();
        </script>
        <!-- animated-css -->
    </head>
    <body>
        <!--header start here-->


        <div class="banner">

            <div class="header">
                <div class="container">
                    <div class="header-main">
                        <div class="logo wow bounceInLeft" data-wow-delay="0.3s">
                            <a href="index.html"><img src="images/logok.png"  alt=""></a>
                        </div>
                        <div class="top-navg">
                            <span class="menu"> <img src="images/icon.png" alt=""/></span>	
                            <nav class="cl-effect-13">				
                                <ul class="res">
                                    <style>
                                        div.b{
                                            text-align: left;
                                            font-weight: 600;
                                            position: relative;
                                            font-family: 'Titillium Web', sans-serif;
                                            font-size: 36px;
                                            line-height: 10px;
                                            padding: 10px 15px 15px 15%;
                                            color:#31b0d5;
                                        }
                                    </style>
                                    <div class="b">
                                        <li>Anti Cam</li>
                                    </div>

                                    <style>
                                        div.a{
                                            text-align: right;
                                        }
                                    </style>   

                                    <div class="a">
                                        <li><a class="active" href="#login">Login</a></li>
                                    </div>

                                </ul>
                            </nav>
                            <!-- script-for-menu -->
                            <script>
                                $("span.menu").click(function () {
                                    $("ul.res").slideToggle(300, function () {
                                        // Animation complete.
                                    });
                                });
                            </script>					
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>

            <div class="banner-main">
                <div class="slider-bann wow bounceInRight" data-wow-delay="0.3s">
                    <ul class="rslides" id="slider2">
                        <li>
                            <h3>Welcome to Kerala Tourism</h3>
                            <h4>Experience Kerala</h4>

                        </li>
                        <li>
                            <h3>Find Beautiful Place</h3>
                            <h4>The Best Way To Be Lost</h4>

                        </li>
                        <li>
                            <h3>To travel is to live</h3>
                            <h4>The Best Way To Be Lost</h4>

                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--header end here-->


        <!--features strat here-->
        <div class="features">
            <div class="container">
                <div class="features-main">
                    <div class="features-top">
                        <h3>Sign in</h3>
                        <span class="lft-bar-fea"> </span>
                        <span class="rit-bar-fea"> </span>
                    </div>
                    <div class="features-bottom" id="login">
                        <!--edit start ...-->

                        <center>
                            <div class="advice-lidt-bottom wow bounceInLeft" data-wow-delay="0.3s">
                                <form action="Process/LoginAction.jsp"  method="post" >
                                    <table id="cstable">
                                        <tr> <td align="center"> <input type="text" name="uname" class="form-control_edited" placeholder=" UserName" required=""></td></tr>
                                        <tr> <td align="center"> <input type="password" name="pass" class="form-control_edited" placeholder=" Password" required=""></td></tr>
                                        <tr> <td  align="center"> <input type="submit" class="btn_submit_edited" value="Login" ></td></tr>
                                    </table>
                                </form>
                                <br><br>
                                </center>

                            </div>    
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