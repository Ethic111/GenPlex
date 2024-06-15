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
    	console.log(blob);
        const url = window.URL.createObjectURL(blob);
        console.log(url);
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
