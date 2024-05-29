
//Rejected MODAL:

	function showRejectModal(doctorId) {
		Swal.fire({
	        html: `
	            <form id="rejectionForm">
	                <div class="form-group">
	                    <label for="rejectionReason"><h3>Reason For Rejection:</h3></label>
	                    <textarea id="rejectionReason" class="form-control" required></textarea>
	                </div>
	            </form>
	        `,
	        icon: 'question',
	        showCancelButton: true,
	        confirmButtonText: 'Submit & Reject',
	        cancelButtonText: 'Cancel',
	        preConfirm: () => {
	            const rejectionReason = Swal.getPopup().querySelector('#rejectionReason').value;
	            if (!rejectionReason) {
	                Swal.showValidationMessage('Reason for rejection is required');
	            }
	            return rejectionReason;
	        }
	    }).then((result) => {
	    	 if (result.isConfirmed) {
	             const reason = result.value;
	             fetch(`doctorRejected?id=${doctorId}&reason=${reason}`, {
	                 method: 'POST'
	             }).then(response => {
	                 if (!response.ok) {
	                     throw new Error(response.statusText);
	                 }
	                 /* return response.json(); */
	                 return response.text();
	             }).then(() =>{
	                 Swal.fire(
	                     'Rejected!',
	                     'The doctor has been rejected.',
	                     'success'
	                 ).then(() => {
	                     ajaxfunc("NOT_REVIEWED");
	                 });
	             }).catch(error => {
	                 Swal.fire('Error!', `Request failed: ${error}`, 'error');
	             });
	         }
	    });
	    
	}
	
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

function updateTableOnSelect(data,table,reviewStatus){
	let dataset = [];
	data.forEach((item, index)=>{
    	
        console.log(item);
    	let actionButtons = '';
        if (item.reviewStatus === 'ACCEPTED') {
            actionButtons = `<a href="deleteDoctor?id=${item.id}"><i
				class="mdi mdi-delete-sweep"
				style="color: red; font-size: 1.5rem;"></i></a>`;
        } else if (item.reviewStatus === 'NOT_REVIEWED') {
            actionButtons = `<a href="doctorAccepted?id=${item.id}">
				<button class="btn btn-success">Accept</button>
				</a>
				
					<button class="btn btn-danger" onclick="showRejectModal(${item.id})">Reject</button>
				`;
        }
        else{  
        	actionButtons = item.reason;
        }
        
        dataset.push([
                      index + 1,
                      item.doctorName,
                      item.statevo.stateName,
                      item.cityvo.cityName,
                      item.degreevo.degreeName,
                      actionButtons,
                  ]);
        /*
		 * dataset.push([ 'hi','hello','hello world','world', 'world hello',
		 * 'hw' ]);
		 */
    });
	 const columnName = reviewStatus === 'REJECTED' ? 'Rejection Reason' : 'Action';
	 table.columns(5).header().to$().text(columnName);
	    
	 table.rows.add(dataset).draw();
}


$(document).ready(function() {
$("#reviewstatus_select").on('change', (selectElement) => {
		console.log('hi there3');
        const reviewStatus = selectElement.target.value;
        console.log(reviewStatus, 'selected element'); 
       
        ajaxfunc(reviewStatus);
                 
	})
})
	
	