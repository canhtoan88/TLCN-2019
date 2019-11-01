$(document).ready(function() {
    $("#ddlProvince").change(function() {

        idCity = $("#ddlProvince").val();
        idDistrict = 0;
        idStreet = 0;

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/247hostel/api/changeCity",
            data: {
                idCity: idCity,
            },
            success: function(value) {

                changeContentByAjax("#ddlDistrict", value, searchHostels);
            }
        });
    });
});

$(document).ready(function() {
    $("#ddlDistrict").change(function() {
        idDistrict = $("#ddlDistrict").val();
        idStreet = 0;

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/247hostel/api/changeDistrict",
            data: {
                idDistrict: idDistrict,
            },
            success: function(value) {

                changeContentByAjax("#ddlStreet", value, searchHostels);
            }
        });
    });
});

function changeContentByAjax(id, value, callback) {

    $(id).empty();
    $(id).append(value);

    callback();
}

$(document).ready(function() {
    $("#ddlStreet").change(function() {

        idStreet = $("#ddlStreet").val();

        searchHostels();
    });
});

$(document).ready(function() {
    $("#ddlPrice").change(function() {

        idRangePrice = $("#ddlPrice").val();

        searchHostels();
        createFullMap();
    });
});

$(document).ready(function() {
    $("#ddlSpace").change(function() {

        idRangeSpace = $("#ddlSpace").val();

        searchHostels();
        createFullMap();
    });
});

$(document).ready(function() {
    $("#ddlDistance").change(function() {

        distance = Number($("#ddlDistance").val());

        if (distance == 1000) {
            zoom = 16;
            createFullMap();
        } else if (distance == 2000) {
            zoom = 15;
            createFullMap();
        } else if (distance == 5000) {
            zoom = 14;
            createFullMap();
        } else if (distance == 10000) {
            zoom = 13;
            createFullMap();
        } else if (distance == 20000) {
            zoom = 12;
            createFullMap();
        }

    });
});

$(document).ready(function() {
    $("#field-results-title-dropdown").change(function() {

        let value = $("#field-results-title-dropdown").val();

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/247hostel/api/resultsDropdownFilterChange",
            data: {
                value: value,
            },
            success: function(value) {

                $("#field-results-list").empty();
                $("#field-results-list").append(value);
            }
        });
    });
});

function searchHostels() {

    var _idCity = idCity;
    var _idDistrict = idDistrict;
    var _idStrict = idStreet;
    var _idRangePrice = idRangePrice;
    var _idRangeSpace = idRangeSpace;
    var _distance = distance;

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/247hostel/api/searchHostels",
        data: {
            idCity: _idCity,
            idDistrict: _idDistrict,
            idStreet: _idStrict,
            idRangePrice: _idRangePrice,
            idRangeSpace: _idRangeSpace,
            distance: _distance
        },
        success: function(value) {

            $("#field-results-list").empty();
            $("#field-results-list").append(value);
        }
    });
};

function searchLocation(event) {
    let x = document.getElementById("input-search");

    if (event.keyCode == 13) {
        searchPlace(x.value);
    }
}