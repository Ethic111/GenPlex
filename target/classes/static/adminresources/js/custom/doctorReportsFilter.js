$(document).ready(function() {
    // Initialize Select2 for select elements
    $('.form-select').select2();
    
 // Event listener for the view link
    $(document).on('click', '.view-link', function() {
        const reportType = $(this).data('report-type');
        const reportPath = $(this).data('report-path');

        // Set modal content
        $('#reportModalLabel').text(reportType);
        $('#reportModalImage').attr('src', reportPath);
        $('#reportModalDownload').attr('href', reportPath);
    });
    

    let cityStateValue = 0;
    let patientDoctor = 0;
    let reportType = 0;

    // Event Handlers for filters
    $("#reporttype_select").on('change', (selectElement) => {
        reportType = selectElement.target.value;
        console.log(reportType);
        ajaxfunc(cityStateValue, patientDoctor, reportType);
    });

    $("#patient_select").on('change', (selectElement) => {
        patientDoctor = selectElement.target.value;
        ajaxfunc(cityStateValue, patientDoctor, reportType);
    });

    $("#citystate_select").on('change', (selectElement) => {
        cityStateValue = selectElement.target.value;
        ajaxfunc(cityStateValue, patientDoctor, reportType);
    });
});

// FILTERING Reports AS PER THEIR State/City/Patient/Doctor/ReportType
function ajaxfunc(cityStateValue, patientDoctor, reportType) {
    $.ajax({
        url: `http://localhost:7072/doctorFilterReports?cityState=${cityStateValue}&patientDoctor=${patientDoctor}&reportType=${reportType}`,
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
            console.error("Error fetching filtered reports:", error);
        }
    });
}

function updateTableOnSelect(data, table) {
    let dataset = [];
    data.forEach((item, index) => {
        const actionButtons = `
            <a class="download-link" href="#" data-file-path="${item.reportPath}" style="padding-right: 12px;">
                <i class="mdi mdi-download" style="color: blue; font-size: 1.4rem;"></i>
            </a>
            <a class="view-link" href="#" data-bs-toggle="modal" data-bs-target="#reportModal"
			 data-report-type="${item.reporttypevo.reportTypeName}" data-report-path="${item.reportPath}"
			 style="padding-right: 12px; cursor: pointer;"> <i class="mdi mdi-eye"
			 style="color: blue; font-size: 1.4rem;"></i>
			</a>
            <a href="deleteReportType?id=${item.id}">
                <i class="mdi mdi-delete-sweep" style="color: red; font-size: 1.5rem;"></i>
            </a>
            <a href="editReportType?id=${item.id}" style="margin-left: 1rem;">
                <i class="mdi mdi-table-edit" style="color: blue; font-size: 1.5rem;"></i>
            </a>`;
        
        const row = [
            index + 1,
            item.reporttypevo.reportTypeName,
            item.date,
            item.patientdoctorvo.patientvo.email,
            item.statevo.stateName,
            item.cityvo.cityName,
            item.summary,
            actionButtons
        ];

        dataset.push(row);
    });

    const columnHeaders = [
        '#',
        'Report Type',
        'Report Date',
        'Patient',
        'State',
        'City',
        'Summary',
        'Action'
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

// File Download

function convertBackslashesToSlashes(path) {
    return path.replace(/\\/g, '/');
}

function addDownloadEventListeners() {
    const downloadLinks = document.querySelectorAll('.download-link');

    downloadLinks.forEach(link => {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            let filePath = this.getAttribute('data-file-path');
            filePath = convertBackslashesToSlashes(filePath);
            downloadFile(filePath);
        });
    });
}

function downloadFile(filePath) {
    fetch(filePath, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            return response.blob();
        }
        if (response.status === 404) {
            throw new Error('File not found');
        }
        throw new Error('File download failed');
    })
    .then(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.style.display = 'none';
        a.href = url;
        a.download = filePath.split('/').pop(); // Use the file name from the
												// path
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
    })
    .catch(error => {
        console.error('There was an error downloading the file:', error);
        alert('Error downloading file: ' + error.message);
    });
}

$(document).ready(function() {
    addDownloadEventListeners();
});

