<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<meta name="description"
	content="Responsive HTML Admin Dashboard Template based on Bootstrap 5" />
<meta name="author" content="NobleUI" />
<meta name="keywords"
	content="nobleui, bootstrap, bootstrap 5, bootstrap5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web" />

<title>Patient | Reports</title>

<!-- Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
	rel="stylesheet" />
<!-- End fonts -->

<!-- core:css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/core.css" />
<!-- endinject -->

<!-- Plugin css for this page -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/dataTables.bootstrap5.css" />
<!-- End plugin css for this page -->

<!-- inject:css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/select2.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/flag-icon.min.css" />
<!-- endinject -->

<!-- Layout styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/style.min.css" />
<!-- End layout styles -->

<%-- <link rel="shortcut icon" href="<%=request.getContextPath()%>/adminresources/images/favicon.png" /> --%>
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/adminresources/images/genplexLOGO.png" />
</head>
<body>
	<div class="main-wrapper">
		<!-- partial:../../partials/_sidebar.html -->

		<jsp:include page="menu.jsp"></jsp:include>


		<!-- partial -->

		<div class="page-wrapper">
			<!-- partial:../../partials/_navbar.html -->
			<jsp:include page="header.jsp"></jsp:include>
			<!-- partial -->

			<div class="page-content">

				<div class="d-flex justify-content-between mb-2">

					<div>
						<div class="row mb-3">
							<h3 class="page-title">Reports</h3>
						</div>



						<nav class="page-breadcrumb">

							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Manage Reports</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Reports</li>
							</ol>


						</nav>
					</div>

					<div class="row m-2">
						<div class="col-7 mr-5">
							<select class="form-select" name="search_reportType"
								id="reporttype_select">
								<option value=0>All Report Types</option>
								<c:forEach items="${reportTypeList}" var="i" varStatus="j">
									<option value="${i.id}">${i.reportTypeName}</option>
								</c:forEach>

							</select>
						</div>

						<div class="col-5">
							<select class="form-select" name="search_doctor"
								id="doctor_select">
								<option value=0>All Doctors</option>
								<c:forEach items="${doctorList}" var="x" varStatus="y">
									<option value="${x.id}">${x.email}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 grid-margin stretch-card">
						<div class="card">
							<div class="card-body">

								<div class="table-responsive">
									<table id="dataTableExample" class="table"
										style="font-size: 0.9rem;">
										<thead>
											<tr>
												<th>#</th>
												<th>Report Type</th>
												<th>Report Date</th>
												<th>Doctor</th>
												<th>Summary</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${reportList}" var="i" varStatus="j">
												<tr>
													<td>${j.count}</td>
													<td>${i.reporttypevo.reportTypeName}</td>
													<td>${i.date}</td>
													<td>${i.patientdoctorvo.doctorvo.email}</td>
													<%-- <td>${i.statevo.stateName}</td>
													<td>${i.cityvo.cityName}</td> --%>
													<td class="text-wrap">${i.summary}</td>
													<td><a class="download-link" href="#"
														data-file-path="${i.reportPath}"
														style="padding-right: 12px;"><i
															class="mdi mdi-download"
															style="color: blue; font-size: 1.4rem;"></i></a> <a
														class="view-link" href="#" data-bs-toggle="modal"
														data-bs-target="#reportModal"
														data-report-type="${i.reporttypevo.reportTypeName}"
														data-report-path="${i.reportPath}"
														style="padding-right: 12px; cursor: pointer;"> <i
															class="mdi mdi-eye"
															style="color: blue; font-size: 1.4rem;"></i>
													</a></td>
												</tr>



											</c:forEach>

										</tbody>
									</table>

									<!-- Modal -->
									<div class="modal fade" id="reportModal" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title text-primary" id="reportModalLabel"></h4>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">
													<img id="reportModalImage" src="" alt="Report Image"
														style="height: 28rem; width: 29rem;" />
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Close</button>
													<a id="reportModalDownload" class="btn btn-primary"
														href="#" download>Download</a>
												</div>
											</div>
										</div>
									</div>
									<!-- End Modal -->

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- partial:partials/_footer.html -->
			<jsp:include page="footer.jsp"></jsp:include>
			<!-- partial -->
			<!-- partial -->
		</div>
	</div>

	<!-- core:js -->
	<script src="<%=request.getContextPath()%>/adminresources/js/core.js"></script>
	<!-- endinject -->

	<!-- Plugin js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/jquery.dataTables.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/dataTables.bootstrap5.js"></script>
	<!-- End plugin js for this page -->

	<!-- inject:js -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/select2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/feather.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/template.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/data-table.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/select2.js"></script>
	<!-- End custom js for this page -->
	<!-- Custom js for this page -->
	<%-- 	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/downloadFile.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/patientReportsFilter.js"></script>
	<!-- End custom js for this page -->
</body>
</html>
