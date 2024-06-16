$(document).ready(function() {
	// Initialize Select2 for select elements
	$('.form-select').select2();

	/*
	 * if ($('.form-select').data('select2')) { console.log('Select2 initialized
	 * successfully'); } else { console.log('Select2 initialization failed'); }
	 */
	$("#search_select").on('change', (selectElement) => {
        const filterValue = selectElement.target.value;
        if (filterValue === "all" ) {
        	location.reload();   
        }else{
        	ajaxfunc(filterValue);
        }
    });
	
});


// FILTERING Patients AS PER THEIR State/City
function ajaxfunc(filterValue) {
    $.ajax({
        url: `http://localhost:7072/filterPatients/${filterValue}`,
        method: 'GET',
        success: function(response) {
            if (response.status) {
                const data = response.body;
                const table = $('#dataTableExample').DataTable();
                table.clear().draw();
                updateTableOnSelect(data, table);
            }
        },
        error: function(xhr, status, error) {
            console.error("Error fetching filtered patients:", error);
        }
    });
}

function updateTableOnSelect(data, table) {
    let dataset = [];
    data.forEach((item, index) => {
        const actionButtons = `<a href="deletePatient?pid=${item.id}"><i class="mdi mdi-delete-sweep" style="color: red; font-size: 1.5rem;"></i></a>`;
        
        const row = [
            index + 1,
            item.email,
            item.statevo.stateName,
            item.cityvo.cityName,
            actionButtons
        ];

        dataset.push(row);
    });

    const columnHeaders = [
        '#',
        'EMAIL',
        'STATE',
        'CITY',
        'ACTION'
    ];

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