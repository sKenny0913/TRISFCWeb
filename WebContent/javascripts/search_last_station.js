$(document).ready(function () {
    $("#btnSearch").click(function () {
        $("#idDivDetails").hide();
        var strRStationURL = "http://trisfc.qsinc.com.tw:8080/TRISFCWeb/qryLastStation";
        var strCBName = $(this).closest("form").find("input[name='SerialNumber']").val();
        var url = strRStationURL + '?SerialNumber=' + strCBName;
        //        console.log(url);
        w3.getHttpObject(url, displayRStation);
    });
    $('#idForm').keypress(function (e) {
        if (e.which == 13) {
            alert("Please click search button.");
        }
    });
});
$(document).on('click', '.w3-medium', function () {
    var strCBName = $(this).find("td:eq(0)").text().trim();
    var strStation = $(this).find("td:eq(1)").text().trim();
    var uploadtimes = $(this).find("td:eq(4)").text().trim();
    var strRDetailURL = "http://trisfc.qsinc.com.tw:8080/TRISFCWeb/qryDetail";
    var url = strRDetailURL + '?Station=' + strStation + '&SerialNumber=' + strCBName + '&Uploadtimes=' + uploadtimes;
    //    console.log(url);
    w3.getHttpObject(url, displayRDetail);
});

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