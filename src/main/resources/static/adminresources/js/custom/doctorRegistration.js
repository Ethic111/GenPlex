$(function() {
	
	// Validate add state form on keyup and submit
	$("#doctorRegistrationForm").validate({
		rules : {
			doctorName : {
				required : true,
				minlength : 3
			},
			email : {
				required : true,
				email : true
			},
			stateId : {
				required : true,
				min : 1
			},
			cityId : {
				required : true,
				min : 1
			},
			degreeId : {
				required : true,
				min : 1
			}
		},
		messages : {
			doctorName : {
				required : "Please enter your name",
				minlength : "Name must consist of at least 3 characters"
			},
			email : {
				required : "Please enter your email",
				email : "Please enter a valid email address"
			},
			stateId : {
				required : "Please select your state",
				min : "Please select a valid state"
			},
			cityId : {
				required : "Please select your city",
				min : "Please select a valid city"
			},
			degreeId : {
				required : "Please select your degree",
				min : "Please select a valid degree"
			}
		}
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
						if (cityId > 0) {
							updateState(cityId);
						}
					});

					function updateCities(stateId) {
						var xhr = new XMLHttpRequest();
						xhr.open('GET', 'api/cities?state='
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

				});
