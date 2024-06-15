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

<title>Admin | Reports</title>

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
	href="<%=request.getContextPath()%>/adminresources/css/select2.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/dataTables.bootstrap5.css" />
<!-- End plugin css for this page -->

<!-- inject:css -->
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
						<div class="col-4 mr-5">
							<select class="form-select" name="search_reportType"
								id="reporttype_select">
								<option value=0>All Report Types</option>
								<c:forEach items="${reportTypeList}" var="i" varStatus="j">
									<option value="${i.id}">${i.reportTypeName}</option>
								</c:forEach>

							</select>
						</div>
						<div class="col-4">
							<select class="form-select" name="search_cityState"
								id="citystate_select">
								<option value=0>All City-State</option>
								<c:forEach items="${cityList}" var="i" varStatus="j">
									<option value="${i.id}">${i.cityName}</option>
								</c:forEach>
								<c:forEach items="${stateList}" var="x" varStatus="y">
									<option value="${x.id}">${x.stateName}</option>
								</c:forEach>
							</select>
						</div>

						<div class="col-4">
							<select class="form-select" name="search_patientDoctor"
								id="patientdoctor_select">
								<option value=0>All Patient-Doctors</option>
								<c:forEach items="${patientList}" var="i" varStatus="j">
									<option value="${i.id}">${i.email}</option>
								</c:forEach>
								<c:forEach items="${doctorList}" var="x" varStatus="y">
									<option value="${x.id}">${x.email}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<!-- <a href="addReport"><button class="btn btn-primary mt-2"
							style="padding-right: 2rem; padding-left: 2rem;">Add</button></a> -->
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
												<th>Patient</th>
												<th>Doctor</th>
												<th>State</th>
												<th>City</th>
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
													<td>${i.patientdoctorvo.patientvo.email}</td>
													<td>${i.patientdoctorvo.doctorvo.email}</td>
													<td>${i.statevo.stateName}</td>
													<td>${i.cityvo.cityName}</td>
													<td>${i.summary}</td>
													<td><a class="download-link" href="#"
														data-file-path="${i.reportPath}"
														style="padding-right: 12px;"><i
															class="mdi mdi-download"
															style="color: blue; font-size: 1.4rem;"></i></a><a
														href="deleteReportType?id=${i.id}"><i
															class="mdi mdi-delete-sweep"
															style="color: red; font-size: 1.5rem;"></i></a> <a
														href="editReportType?id=${i.id}"
														style="margin-left: 1rem;"><i
															class="mdi mdi-table-edit"
															style="color: blue; font-size: 1.5rem;"></i> </a></td>
												</tr>
											</c:forEach>

											<!-- <td>Hii</td>
											<td>Hii</td>
											<td><a href="delete"><i class="mdi mdi-delete-sweep"
													style="color: red; font-size: 1.5rem;"></i></a> <a href="edit"
												style="margin-left: 1rem;"><i class="mdi mdi-table-edit"
													style="color: blue; font-size: 1.5rem;"></i> </a></td> -->
										</tbody>
									</table>
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
		src="<%=request.getContextPath()%>/adminresources/js/feather.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/template.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/jquery.inputmask.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/select2.min.js"></script>
	<!-- endinject -->

	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/select2.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/data-table.js"></script>
	<!-- End custom js for this page -->

	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/downloadFile.js"></script>

	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/adminReportsFilter.js"></script>
	<!-- End custom js for this page -->
</body>
</html>
