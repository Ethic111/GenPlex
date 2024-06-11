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
			patientId : {
				required : true,
				min : 1
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
			patientId : {
				required : "Please select the patient",
				min : "Please select a valid patient"
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

/*
 * $(document).ready( function() { // Initialize Select2 for patient dropdown
 * $('#patient_select').select2({ tags : true, // Allow for custom tags
 * createTag : function(params) { return { id : params.term, text : params.term,
 * newOption : true }; }, insertTag : function(data, tag) { data.push(tag); }
 * }).on( 'select2:select', function(e) { var selectedOption = e.params.data;
 * 
 * if (selectedOption.newOption) { // The selected patient is a new entry, call
 * the // patient insertion API var patientEmail = selectedOption.id; var
 * stateId = $('#state_select').val(); var cityId = $('#city_select').val();
 * 
 * $.ajax({ type : "POST", url : "/insertPatient", data : { patientEmail :
 * patientEmail, stateId : stateId, cityId : cityId }, success :
 * function(response) { // Patient inserted successfully, update the // dropdown
 * with the new patient's ID var newOption = new Option(response.email,
 * response.id, true, true); $('#patient_select').append(newOption)
 * .trigger('change');
 *  // Remove the data attribute to prevent // recursive addition
 * $('#patient_select option:selected') .removeData('newOption');
 *  // Reload the page to update the dropdown // values without losing other
 * inputs reloadPageWithInputs(); }, error : function(xhr, status, error) { //
 * Handle error console.error("Error inserting patient: " + error); } }); } });
 * 
 * function reloadPageWithInputs() { var url = new URL(window.location.href);
 * var formData = $('#addReportForm').serializeArray();
 * formData.forEach(function(field) { url.searchParams.set(field.name,
 * field.value); }); window.location.href = url.href; }
 *  // Initialize flatpickr on the input fields with data-input // attribute
 * $("#reportDate").flatpickr({ dateFormat : "Y-m-d", });
 *  // Initialize select2 for select elements $('.form-select').select2(); });
 */