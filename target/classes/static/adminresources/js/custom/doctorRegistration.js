$(function() {
	// validate add state form on keyup and submit
	$("#doctorRegistrationForm").validate({
		rules : {
			doctorName : {
				required : true,
				minlength : 3
			},
			email : {
				required : true
			},
			stateId : {
				required : true,
				min : 0
			},
			cityId : {
				required : true,
				min : 0
			},
			degreeId : {
				required : true,
				min:0
			}
		},
		messages : {
			doctorName : {
				required : "Please enter a your name",
				minlength : "Name must consist of at least 3 characters"
			},
			age : {
				required : "Please enter a your age",
				minlength : "Age must consist of at least 2 digits"
			},
			stateId : {
				required : "Please select your state",
				min : "",
			},
			cityId : {
				required : "Please select your city",
				min : "",
			},
			degreeId : {
				required : "Please enter a your degree",
				min:""
			}

		},

	});
});

// ////////////////// Custom state and city options
// ////////////////////////////////

document
		.addEventListener(
				'DOMContentLoaded',
				function() {
					var stateSelect = document.getElementById('state_select');
					var citySelect = document.getElementById('city_select');
					console.log(stateSelect);
					stateSelect
							.addEventListener(
									'change',
									function() {
										var stateId = stateSelect.value;
										if (stateId > 0) {
											updateCities(stateId);
										} else {
											citySelect.innerHTML = '<option value="">Select your City</option>';
										}
									});

					citySelect.addEventListener('change', function() {
						var cityId = citySelect.value;
						if (cityId >0) {
							updateState(cityId);
						}
					});

					function updateCities(stateId) {
						var xhr = new XMLHttpRequest();
						xhr.open('GET', '/api/cities?state='
								+ encodeURIComponent(stateId), true);
						xhr.onload = function() {
							if (xhr.status === 200) {
								var cities = JSON.parse(xhr.responseText);
								citySelect.innerHTML = '<option value="">Select your City</option>';
								cities.forEach(function(city) {
									var option = document
											.createElement('option');
									option.value = city.id;
									option.text = city.cityName;
									citySelect.appendChild(option);
								});
							} else {
								console.error('Error fetching cities: '
										+ xhr.status);
							}
						};
						xhr.onerror = function() {
							console.error('Request error...');
						};
						xhr.send();
					}

					/*function updateState(cityId) {
						var xhr = new XMLHttpRequest();
						xhr.open('GET', '/api/state?city='
								+ encodeURIComponent(cityId), true);
						xhr.onload = function() {
							if (xhr.status === 200) {
								 var state = JSON.parse(xhr.responseText); 
								stateSelect.value = xhr.responseText;
								 updateCities(state.stateName); 
							} else {
								console.error('Error fetching state: '
										+ xhr.status);
							}
						};
						xhr.onerror = function() {
							console.error('Request error...');
						};
						xhr.send();
					}*/
				});
