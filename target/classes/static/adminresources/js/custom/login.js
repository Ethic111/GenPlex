

function validateForm() {
	// Initialize the validation on the form
	$("#loginForm").validate({
		rules : {
			username : {
				required : true,
				minlength : 2,
			// Assuming username is an email
			},
			password : {
				required : true,
				minlength : 2
			}
		},
		messages : {
			username : {
				required : "Please enter a username",
				minlength : "Username must consist of at least 2 characters",
				email : "Please enter a valid email address"
			},
			password : {
				required : "Please enter a password",
				minlength : "Password must consist of at least 2 characters"
			}
		}

	});

	// Return the validation status
	return $("#loginForm").valid();
}