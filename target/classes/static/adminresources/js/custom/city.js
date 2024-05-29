// Define a custom validation method for word limit
$.validator.addMethod("maxwords", function(value, element, params) {
    var words = value.match(/\S+/g).length; // Count words using regex
    return this.optional(element) || words <= params; // Compare word count with params (maximum words allowed)
}, $.validator.format("Please enter no more than {250} words.")); // Error message format

$(function() {
	// validate add city form on keyup and submit
	$("#addCityForm").validate({
		rules : {
			stateName : {
				required : true,
				min : 0
			},
			cityName : {
				required : true,
				minlength : 3
			},
			cityDescription : {
				required : true,
				minlength : 5,
				maxwords: 250
			}
		},
		messages : {
			stateName : {
				required : "Please select a state name",
				min : ""
			},
			cityName : {
				required : "Please enter a city name",
				minlength : "Name must consist of at least 3 characters"
			},
			cityDescription : {
				required : "Please enter description of city",
				minlength : "Description must consist of at least 5 characters",
				maxwords: "Description cannot exceed {250} words"
			}

		},
	});
});


