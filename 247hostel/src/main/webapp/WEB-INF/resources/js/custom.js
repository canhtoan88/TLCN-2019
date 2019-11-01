$(document).ready(function() {
	$("#ddlProvince").change(function() {

		var idCity = $("#ddlProvince").val();

		$.ajax({
			type : "GET",
			url : "http://localhost:8080/NhaTroRe/api/changeCity",
			data : {
				idCity : idCity,
			},
			success : function(value) {

				// alert(value);

				$("#ddlDistrict").empty();
				$("#ddlDistrict").append(value);
			}
		});
	});
});

$(document).ready(function() {
	$("#ddlDistrict").change(function() {

		var idDistrict = $("#ddlDistrict").val();

		$.ajax({
			type : "GET",
			url : "http://localhost:8080/NhaTroRe/api/changeDistrict",
			data : {
				idDistrict : idDistrict,
			},
			success : function(value) {

				// alert(value);

				$("#ddlStreet").empty();
				$("#ddlStreet").append(value);
			}
		});
	});
});


