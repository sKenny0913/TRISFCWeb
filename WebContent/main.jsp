<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <!DOCTYPE html>
    <html>
    <title>TRI SFC Web</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/w3.css">
    <link rel="stylesheet" href="css/Montserrat.css">
    <link rel="stylesheet" href="css/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
    <link rel="stylesheet" href="css/search.css">
    <script src="javascripts/w3.js"></script>
    <script src="javascripts/jquery.min.js"></script>
    <script src="javascripts/search.js"></script>
    <style>
        body,
        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            font-family: "Montserrat", sans-serif
        }
        
        .w3-row-padding img {
            margin-bottom: 12px
        }
        /* Set the width of the sidebar to 120px */
        
        .w3-sidebar {
            width: 120px;
            background: #222;
        }
        /* Add a left margin to the "page content" that matches the width of the sidebar (120px) */
        
        #main {
            margin-left: 120px
        }
        /* Remove margins from "page content" on small screens */
        
        @media only screen and (max-width: 600px) {
            #main {
                margin-left: 0
            }
        }
    </style>

    <body class="w3-black">
        <!-- Icon Bar (Sidebar - hidden on small screens) -->
        <nav class="w3-sidebar w3-bar-block w3-small w3-hide-small w3-center">
            <!-- Avatar image in top left corner -->
            <!--  <img src="/w3images/avatar_smoke.jpg" style="width:100%">-->
            <a href="Main" class="w3-bar-item w3-button w3-padding-large w3-hover-black"> <i class="fa fa-home w3-xxlarge"></i>
                <p>HOME</p>
            </a>
            <a target="_top" href="searchAll" class="w3-bar-item w3-button w3-padding-large w3-hover-black"> <i class="fa fa-search w3-xxlarge"></i>
                <p>Search All</p>
            </a>
            <a target="_top" href="searchLast" class="w3-bar-item w3-button w3-padding-large w3-hover-black"> <i class="fa fa-search w3-xxlarge"></i>
                <p>Search Last</p>
            </a>
        </nav>
        <!-- Navbar on small screens (Hidden on medium and large screens) -->
        <div class="w3-top w3-hide-large w3-hide-medium" id="myNavbar">
            <div class="w3-bar w3-black w3-opacity w3-hover-opacity-off w3-center w3-small"> <a href="Main" class="w3-bar-item w3-button" style="width:25% !important">HOME</a> <a href="searchAll" class="w3-bar-item w3-button" style="width:25% !important">Search All</a> <a href="searchLast" class="w3-bar-item w3-button" style="width:25% !important">Search Last</a> </div>
        </div>
        <!-- Page Content -->
        <div class="w3-padding-large" id="main">
            <!-- Header/Home -->
            <header class="w3-container w3-padding-32 w3-center w3-black" id="home">
                <h1 class="w3-jumbo">TRI SFC Web</h1>
                <p>Designed by ¶Àº~à± Kenny.Huang</p>
            </header>
            <!-- Contact Section -->
            <div class="w3-padding-16 w3-content w3-text-grey" id="contact">
                <h2 class="w3-text-light-grey">Administrator</h2>
                <hr style="width:200px" class="w3-opacity">
                <div class="w3-section">
                    <p><i class="fa fa-map-marker fa-fw w3-text-white w3-xxlarge w3-margin-right"></i> QSI MIC</p>
                    <p><i class="fa fa-phone fa-fw w3-text-white w3-xxlarge w3-margin-right"></i> Ext: 11706</p>
                    <p><i class="fa fa-envelope fa-fw w3-text-white w3-xxlarge w3-margin-right"> </i> Email: kenny.huang@qsitw.com</p>
                </div>
                <br>
                <!-- End Contact Section -->
            </div>
            <!-- Footer -->
            <footer class="w3-content w3-padding-8 w3-text-grey w3-xlarge">
                <p class="w3-medium">Powered by QSI MIC</p>
                <!-- End footer -->
            </footer>
            <!-- END PAGE CONTENT -->
        </div>
    </body>

    </html>