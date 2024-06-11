$(document).ready(function() {

	// Function to check profile status and update links
	function checkProfileStatus() {
		$.ajax({
			url : '/api/profileStatus',
			method : 'GET',
			success : function(response) {
				// Redirect to profile controller
				if (response) {
					// Redirect to profile if the profile status is true
					/*window.location.href = 'profile';*/
				}
			},
			error : function() {
				console.error('Error fetching profile status.');
			}
		});
	}

	// Initial check on page load
	checkProfileStatus();

	/*// Form submission handler to update profile
	$('#completeProfileForm').on('submit', function(e) {
		e.preventDefault();

		var formData = new FormData(this);
		console.log(formData);
		$.ajax({
			url : $(this).attr('action'),
			method : $(this).attr('method'),
			data : formData,
			processData : false,
			contentType : false,
			success : function(response) {
				console.log("response:")
				console.log(response);
				checkProfileStatus();
				window.location.href = 'profile';
				
				 * if (response === true) { // Profile completed, recheck
				 * profile status to restore links checkProfileStatus();
				 * window.location.href = '<%=request.getContextPath()%>/doctor/profile'; }
				 
			},
			error : function() {
				console.error('Error submitting profile.');
			}
		});
	});*/
});
