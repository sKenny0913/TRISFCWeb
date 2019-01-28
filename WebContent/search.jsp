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
        /* Add a left margin to the"page content" that matches the width of the sidebar (120px) */
        
        #main {
            margin-left: 120px
        }
        /* Remove margins from"page content" on small screens */
        
        @media only screen and (max-width: 600px) {
            #main {
                margin-left: 0
            }
        }
    </style>

    <body class="w3-black">
        <script src="javascripts/w3.js"></script>
        <script src="javascripts/jquery.min.js"></script>
        <script src="javascripts/search.js"></script>
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
                <h1 class="w3-jumbo">Search All</h1>
                <p>search robot/joint log in specific station</p>
            </header>
            <!-- Contact Section -->
            <!--        <div class="w3-padding-16 w3-col l6 m8">-->
            <form id="idForm">
                <div class="w3-row-padding" style="margin:8px -16px;">
                    <div class="w3-third ">
                        <label> Station:</label>
                        <select id="idStationList" class="w3-input w3-border">
                            <option w3-repeat="stationList">{{station}}</option>
                        </select>
                    </div>
                    <div class="w3-third w3-margin-bottom">
                        <label>Column Name:</label>
                        <input class="w3-input w3-border" type="text" name="Column_Name" id="Column_Name"> </div>
                    <div class="w3-third w3-margin-bottom">
                        <label><i class="fa fa-calendar-o"></i> From:</label>
                        <input class="w3-input w3-border" type="date" name="fday" required placeholder="YYYY-MM-DD"> </div>
                    <div class="w3-third  w3-margin-bottom">
                        <label>SerialNumber:</label>
                        <input class="w3-input w3-border" type="text" name="SerialNumber" id="idCBName"> </div>
                    <div class="w3-third w3-margin-bottom">
                        <label>Column Value:</label>
                        <input class="w3-input w3-border" type="text" name="Column_Value" id="Column_Value"> </div>
                    <div class="w3-third w3-margin-bottom">
                        <label><i class="fa fa-calendar-o"></i> To:</label>
                        <input class="w3-input w3-border" type="date" name="tday" required placeholder="YYYY-MM-DD"> </div>
                </div>
                <input type="button" class="w3-button w3-dark-grey" id="btnSearch" value="Search"> </form>
            <!--        </div>-->
            <div id="idResult" style="display:none">
                <h2 class="w3-text-light-grey">Result:</h2>
                <hr style="width:200px" class="w3-opacity">
                <div class="w3-section w3-responsive" id="idDivStations">
                    <table class="w3-table-all w3-light-grey w3-hoverable" id="idStations">
                        <tr class="w3-large">
                            <th>SerialNumber</th>
                            <th>Station</th>
                            <th>Result</th>
                            <th>DateTime</th>
                            <th>Uploadtimes</th>
                        </tr>
                        <tr class="w3-medium" w3-repeat="stations">
                            <td>{{SerialNumber}}</td>
                            <td>{{Station}}</td>
                            <td>{{VALUE}}</td>
                            <td>{{date}}</td>
                            <td>{{uploadtimes}}</td>
                        </tr>
                    </table>
                </div>
                <button onclick="window.location.href='#idForm'">TOP</button>
                <hr style="width:200px" class="w3-opacity"> </div>
            <div class="w3-section w3-responsive" id="idDivDetails" style="display:none">
                <table class="w3-table-all w3-light-grey w3-hoverable" id="idDetails">
                    <tr class="w3-large">
                        <th>Details:</th>
                    </tr>
                    <tr class="w3-small" w3-repeat="details">
                        <td style="width:60px">{{key}}</td>
                        <td>{{value}}</td>
                    </tr>
                </table>
            </div>
            <!-- End Contact Section -->
            <!-- Footer -->
            <footer class="w3-content w3-padding-8 w3-text-grey w3-xlarge">
                <p class="w3-medium">Powered by QSI MIC</p>
                <!-- End footer -->
            </footer>
            <!-- END PAGE CONTENT -->
        </div>
        <!--
    <div id="idLoader" class="w3-modal">
    <div class="w3-modal-content w3-display-middle w3-black"> <span onclick="document.getElementById('idLoader').style.display='none'" class=" w3-large w3-button w3-display-topright">&times;</span>
        <div class="w3-container w3-padding-64"> <i class="fa fa-spinner fa-pulse fa-2x fa-fw"></i> <span class="w3-xlarge">Loading...</span> </div>
    </div>
</div>
-->
    </body>

    </html>