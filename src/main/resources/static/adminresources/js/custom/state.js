$(function() {
	// validate add state form on keyup and submit
	$("#addStateForm").validate({
		rules : {
			stateName : {
				required : true,
				minlength : 3
			},
			stateDescription : {
				required : true,
				minlength : 5
			}
		},
		messages : {
			stateName : {
				required : "Please enter a state name",
				minlength : "Name must consist of at least 3 characters"
			},
			stateDescription : {
				required : "Please enter description of state",
				minlength : "Description must consist of at least 5 characters"
			}

		},
		
	});
});
