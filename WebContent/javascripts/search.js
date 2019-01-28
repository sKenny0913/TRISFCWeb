//var serverAddress = "http://localhost:8085/TRISFCWeb/";
var serverAddress = "http://trisfc.qsinc.com.tw:8080/TRISFCWeb/";
var strStation;
$(document).ready(function () {
    var urlStations = serverAddress + "qryStation";
    w3.getHttpObject(urlStations, displayStationList);
    $("#btnSearch").click(function () {
        $("#idDivDetails").hide();
        var strRStationURL = serverAddress + "qryHead";
        strStation = $(this).closest("form").find("select[id='idStationList']").val();
        var strCBName = $(this).closest("form").find("input[name='SerialNumber']").val();
        var sColumn_Name = $(this).closest("form").find("input[name='Column_Name']").val();
        var sColumn_Value = $(this).closest("form").find("input[name='Column_Value']").val();
        var fday = $(this).closest("form").find("input[name='fday']").val();
        var tday = $(this).closest("form").find("input[name='tday']").val();
        //        if (fday == "" || tday == "") {
        //            alert("Please enter From/To Date.");
        //        }
        //        else if (fday != "" && tday != "") {
        var url = strRStationURL + '?Station=' + strStation + '&SerialNumber=' + strCBName + '&Column_Name=' + sColumn_Name + '&Column_Value=' + sColumn_Value + '&fDay=' + fday + '&tDay=' + tday;
                console.log(url);
        if (strCBName == "" && (sColumn_Name == "" || sColumn_Value == "")) {
            alert('please input SerialNumber or input ColumnName with ColumnValue.');
        }
        else {
            w3.getHttpObject(url, displayRStation);
        }
        //        }
    });
    $('#idForm').keypress(function (e) {
        if (e.which == 13) {
            alert("Please click search button.");
        }
    });
});
$(document).on('click', '.w3-medium', function () {
    var strCBName = $(this).find("td:eq(0)").text().trim();
    //       var station = $(this).find("td:eq(2)").text().trim();
    var uploadtimes = $(this).find("td:eq(4)").text().trim();
    var strRDetailURL = serverAddress + "qryDetail";
    var url = strRDetailURL + '?Station=' + strStation + '&SerialNumber=' + strCBName + '&Uploadtimes=' + uploadtimes;
    //    console.log(url);
    w3.getHttpObject(url, displayRDetail);
});

function displayStationList(dataArray) {
    //    console.log(dataArray);
    if (dataArray != null) {
        if (dataArray.stationList.length > 0) {
            w3.displayObject("idStationList", dataArray);
        }
        else {
            alert("station list no data, please check.");
        }
    }
    else {
        alert("station list no data, please check.");
    }
}

function displayRStation(dataArray) {
    $("#idLoader").show();
    if (dataArray != null) {
        if (dataArray.stations.length > 0) {
            $("#idLoader").hide();
            w3.displayObject("idStations", dataArray);
            $("#idResult").show();
            $("#idDivStations").show();
        }
        else {
            $("#idLoader").hide();
            $("#idResult").hide();
            alert("station no data, please check.");
        }
    }
    else {
        $("#idLoader").hide();
        $("#idResult").hide();
        alert("station no data, please check.");
    }
}

function displayRDetail(dataArray) {
    $("#idLoader").show();
    if (dataArray != null) {
        if (dataArray.details.length > 0) {
            $("#idLoader").hide();
            w3.displayObject("idDetails", dataArray);
            $("#idDivDetails").show();
            window.location.href = "#idDivDetails";
        }
        else {
            $("#idLoader").hide();
            $("#idDivDetails").hide();
            alert("robot detail no data, please check.");
        }
    }
    else {
        $("#idLoader").hide();
        $("#idDivDetails").hide();
        alert("robot detail no data, please check.");
    }
}