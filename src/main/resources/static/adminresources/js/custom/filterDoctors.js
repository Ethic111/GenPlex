
// Flag to indicate if rejection is in process
let isRejectionInProgress = false;

function validateForm() {
	// Initialize the validation on the form
	 $("#rejectionForm").validate({
			rules : {
				rejectionReason : {
					required : true,
					minlength : 2,
				}
			},
			messages : {
				rejectionReason : {
					required : "Please enter a reason",
					minlength : "Username must consist of at least 2 characters",
				}
			}

		});

	// Return the validation status
	return $("#rejectionForm").valid();
}

// Show reject modal
function showRejectModal(doctorId) {
    const modalElement = document.getElementById('exampleModal');
    const modal = new bootstrap.Modal(modalElement);
    const rejectButton = document.getElementById('submitRejection');
    const errorMessage = document.getElementById('errorMessage');
    

    // Set the doctorId in a data attribute
    modalElement.setAttribute('data-doctor-id', doctorId);

    // Clear previous error message and rejection reason
    errorMessage.textContent = '';
    document.getElementById('rejectionReason').value = '';

    rejectButton.onclick = function() {
    	
        submitRejection(doctorId, modal);
    };

    modal.show();
}

// Show spinner on button
function toggleButtonSpinner(button, show) {
    if (show) {
        button.disabled = true;
        button.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> ' + button.textContent;
    } else {
        button.disabled = false;
        button.innerHTML = button.textContent;
    }
}
// Disable button
function disableButtons(buttons, disable) {
	buttons.forEach(button => {
		
			button.disabled = disable;
		
	})
}

// Submit rejection
function submitRejection(doctorId, modal) {
	if (validateForm()){
    const rejectionReason = document.getElementById('rejectionReason').value;
    const errorMessage = document.getElementById('errorMessage');
    const closeButton = document.querySelector('.modal-footer .btn-secondary');
    const rejectButton = document.getElementById('submitRejection');
    const allRejectButtons = document.querySelectorAll('.reject-btn'); // table
																	// reject
																	// button
    const allAcceptButtons = document.querySelectorAll('.accept-btn'); // table
																			// accept
																			// button
    
    // Set rejection in process flag to true
    isRejectionInProgress = true;
   
    // Show spinner on buttons
    toggleButtonSpinner(closeButton, true);
    toggleButtonSpinner(rejectButton, true);
    
    
    disableButtons([...allAcceptButtons, ...allRejectButtons], true);


    fetch(`doctorRejected?id=${doctorId}&reason=${encodeURIComponent(rejectionReason)}`, {
        method: 'POST'
    })
    .then(() => {
        modal.hide();
        // Reload or refresh the list of doctors to reflect the change
        ajaxfunc('NOT_REVIEWED');

        // Show success alert using SweetAlert2
        Swal.fire({
            icon: 'success',
            title: 'Rejected!',
            text: 'The doctor has been rejected successfully.',
            showConfirmButton: false,
            timer: 1500
        });
    })
    .catch(error => {
        errorMessage.textContent = `Request failed: ${error}`;
    })
    .finally(() => {
        // Hide spinner on buttons
        toggleButtonSpinner(closeButton, false);
        toggleButtonSpinner(rejectButton, false);
        
        // Re-enable all buttons
        disableButtons([...allAcceptButtons, ...allRejectButtons], false);
    });
    }
}

// Accept doctor with spinner and success alert
function acceptDoctor(doctorId) {
	
	 const acceptBtn = document.getElementById('submitAcception');
	 const allRejectButtons = document.querySelectorAll('.reject-btn');
	 const allAcceptButtons = document.querySelectorAll('.accept-btn');
	
	// Disable all buttons
	disableButtons([...allAcceptButtons, ...allRejectButtons], true);
	 
    // Show spinner on button
    toggleButtonSpinner(acceptBtn, true);
    
    fetch(`doctorAccepted?id=${doctorId}`, {
        method: 'GET'
    })
    .then(() => {
        // Assuming the response indicates success
        ajaxfunc('NOT_REVIEWED');

        // Show success alert using SweetAlert2
        Swal.fire({
            icon: 'success',
            title: 'Accepted!',
            text: 'The doctor has been accepted successfully.',
            showConfirmButton: false,
            timer: 1500
        });
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    })
    .finally(() => {
        // Hide spinner on button
        toggleButtonSpinner(acceptBtn, false);
        
        // Re-enable all buttons
        disableButtons([...allAcceptButtons, ...allRejectButtons], false);
    });
}


// Clean up modal state when hidden
document.getElementById('exampleModal').addEventListener('hidden.bs.modal', function () {
    const modalBackdrop = document.querySelector('.modal-backdrop');
    if (modalBackdrop) {
        modalBackdrop.remove();
    }
    document.body.classList.remove('modal-open');
    document.body.style = '';
});


	
// FILTERING DOCTORS AS PER THEIR REVIEW STATUS
	
	
function ajaxfunc(reviewStatus){
	 $.ajax({
         url: "http://localhost:7072/filterReviewStatus/"+reviewStatus,
         method: 'GET',
         success: function(response) {
             if (response.status) {
                 const data = response.body;
                 const table = $('#dataTableExample').DataTable(); 
                 table.clear().draw(); 
                 updateTableOnSelect(data,table,reviewStatus);
             }
         }
     });
	 
}

function updateTableOnSelect(data, table, reviewStatus) {
    let dataset = [];
    data.forEach((item, index) => {
        console.log(item);
        let actionButtons = '';
        if (item.reviewStatus === 'ACCEPTED') {
            actionButtons = `<a href="deleteDoctor?id=${item.id}"><i
                class="mdi mdi-delete-sweep"
                style="color: red; font-size: 1.5rem;"></i></a>`;
        } else if (item.reviewStatus === 'NOT_REVIEWED') {
            actionButtons = `
               <button class="btn btn-success accept-btn" id="submitAcception" onclick="acceptDoctor(${item.id})">Accept</button>
               <button class="btn btn-danger reject-btn" onclick="showRejectModal(${item.id})" data-bs-toggle="modal" data-bs-target="#exampleModal">Reject</button>
            	`;
        } else {
            actionButtons = item.reason;
        }

        const row = [
            index + 1,
            item.doctorName,
            item.statevo.stateName,
            item.cityvo.cityName,
            item.degreevo.degreeName
        ];

        if (reviewStatus === 'ACCEPTED') {
            row.push(item.age);  // Add age for accepted doctors
        }

        row.push(actionButtons);
        dataset.push(row);
    });

    const columnHeaders = [
        '#',
        'DOCTOR NAME',
        'STATE NAME',
        'CITY NAME',
        'DEGREE'
    ];

    if (reviewStatus === 'ACCEPTED') {
        columnHeaders.push('Age');
    }

    columnHeaders.push(reviewStatus === 'REJECTED' ? 'Rejection Reason' : 'Action');

    // Update DataTable headers
    table.clear();
    table.destroy();
    $('#dataTableExample').empty(); // clear the current content of the table

    $('#dataTableExample').DataTable({
        data: dataset,
        columns: columnHeaders.map(header => ({ title: header })),
        createdRow: function (row, data, dataIndex) {
            // Add text-wrap class to all <td> elements in each row
            $('td', row).addClass('text-wrap');
        }
       
    });
    
}


$(document).ready(function() {
$("#reviewstatus_select").on('change', (selectElement) => {
		console.log('hi there3');
        const reviewStatus = selectElement.target.value;
        console.log(reviewStatus, 'selected element'); 
       
        ajaxfunc(reviewStatus);
                 
	})

})
	
	