$(document).ready(function() {
	// Initialize Flatpickr on the input fields with data-input attribute
	$("#reportDate").flatpickr({
		dateFormat : "Y-m-d",
	});

	// Initialize Select2 for select elements
	$('.form-select').select2();

	// Validate add report form on keyup and submit
	$("#addReportForm").validate({
		rules : {
			reportType : {
				required : true,
				min : 1
			},
			date : {
				required : true,
				date : true
			},
			stateId : {
				required : true,
				min : 1
			},
			cityId : {
				required : true,
				min : 1
			},
			patientEmail : {
				required : true,
				email : true
			},
			reportFile : {
				required : true,
				file : true
			},
			summary : {
				required : true,
				minlength : 5
			}
		},
		messages : {
			reportType : {
				required : "Please select a report type",
				min : "Please select a valid report type"
			},
			date : {
				required : "Please enter report date",
				date : "Please enter a valid date"
			},
			stateId : {
				required : "Please select your state",
				min : "Please select a valid state"
			},
			cityId : {
				required : "Please select your city",
				min : "Please select a valid city"
			},
			patientEmail : {
				required : "Please select the patient",
				email : "Please select a valid patient"
			},
			reportFile : {
				required : "Please upload the report file",
				file : "Report must be a file"
			},
			summary : {
				required : "Please enter report's summary",
				minlength : "Summary must consist of at least 5 characters"
			}
		}
	});

	// Custom state and city options
	$('#state_select').change(function() {
		var stateId = $(this).val();
		var citySelect = $('#city_select');
		if (stateId > 0) {
			updateCities(stateId, citySelect);
		} else {
			citySelect.html('<option value="">Select City</option>');
		}
	});

	// $('#patient_select').select2({
	// tags : true,
	// createTag : function(params) {
	// var term = $.trim(params.term);
	// if (term === '') {
	// return null;
	// }
	// return {
	// id : term,
	// text : term,
	// newTag : true
	// };
	// }
	// }).on('select2:selecting', function(e) {
	// var tagEmail = e.params.args.data.id;
	// if (e.params.args.data.newTag) {
	// // Trigger AJAX request to insert the new patient into the database
	// $.ajax({
	// url : 'insertPatient',
	// type : 'POST',
	// data : {
	// patientEmail : tagEmail
	// },
	// success : function(response) {
	// console.log('Patient inserted successfully:', response);
	// },
	// error : function(xhr, status, error) {
	// console.error('Error inserting patient:', error);
	// }
	// });
	// }
	// });
	
	

	/*$(document).on('keyup', '.select2-search__field', function (e) {
		if (e.which === 13) {
	        const stateId = $('#state_select').val();
	        const cityId = $('#city_select').val();
	        const patientEmail = $('.select2-search__field').val().trim();

	        // Validate state and city selection
	        if (stateId <= 0) {
	            showError('Please select a state.');
	            return;
	        } else {
	            clearError();
	        }

	        if (cityId <= 0) {
	            showError('Please select a city.');
	            return;
	        } else {
	            clearError();
	        }

	        if (patientEmail === '') {
	            return;
	        }

	        // Trigger AJAX request to insert the new patient into the database
            $.ajax({
                url: 'http://localhost:7072/doctor/insertPatient',
                type: 'POST',
                data: {
                    patientEmail: patientEmail,
                    stateId: stateId,
                    cityId: cityId
                },
                success: function(response) {
                    console.log('Patient inserted successfully:', response);
                    // Add the new patient to the select options
                    $('#patient_select').append(new Option(response.email, response.id, true, true)).trigger('change');
                },
                error: function(xhr, status, error) {
                    console.error('Error inserting patient:', error);
                    console.log(xhr.responseText); // Log the server response
                }
            });
	    }
		
		function showError(message, field) {
	        $('<span class="text-danger">' + message + '</span>').insertAfter('#' + field);
	    }

	    function clearError(field) {
	        $('#' + field).next('.text-danger').remove();
	    }
	});
	
	*/
	
});

function updateCities(stateId, citySelect) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/cities?state=' + encodeURIComponent(stateId), true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			var cities = JSON.parse(xhr.responseText);
			citySelect.html('<option value="">Select City</option>');
			cities.forEach(function(city) {
				var option = $('<option>').val(city.id).text(city.cityName);
				citySelect.append(option);
			});
		} else {
			console.error('Error fetching cities: ' + xhr.status);
		}
	};
	xhr.onerror = function() {
		console.error('Request error...');
	};
	xhr.send();
}


