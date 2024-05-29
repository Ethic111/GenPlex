$(function() {
	// validate add degree form on keyup and submit
	$("#addDegreeForm").validate({
		rules : {
			degreeName : {
				required : true,
				minlength : 2
			}
		},
		messages : {
			degreeName : {
				required : "Please enter a degree name",
				minlength : "Degree name must consist of atleast 2 characters"
			}
		},
	});
});
