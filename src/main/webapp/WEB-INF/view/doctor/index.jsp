<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<title>Doctor | Dashboard</title>

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

<!-- inject:css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/materialdesignicons.min.css">
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
		<!-- partial:partials/_sidebar.html -->
		<jsp:include page="menu.jsp"></jsp:include>



		<!-- partial -->

		<div class="page-wrapper">
			<!-- partial:partials/_navbar.html -->
			<!-- partial -->
			<jsp:include page="header.jsp"></jsp:include>


			<div class="page-content">
				<!-- Hidden inputs to store JSON data -->
				<input type="hidden" id="barChartLabels" value="${barChartLabels}" />
				<input type="hidden" id="barChartData" value="${barChartData}" /> <input
					type="hidden" id="patientReportbarChartLabels"
					value="${patientReportbarChartLabels}" /> <input type="hidden"
					id="patientReportbarChartData" value="${patientReportbarChartData}" />
				<!-- row -->

				<div
					class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
					<div>
						<h4 class="mb-3 mb-md-0">Welcome to Dashboard</h4>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-xl-12 stretch-card">
						<div class="row flex-grow-1">
							<div class="col-md-4 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<div
											class="d-flex justify-content-between align-items-baseline">
											<h6 class="card-title mb-0">Patients</h6>
											<div class="dropdown mb-2"></div>
										</div>
										<div class="row">
											<div class="col-6 col-md-12 col-xl-5">
												<h3 class="mb-2">${patientCount}</h3>
												<div class="d-flex align-items-baseline">
													<!-- <p class="text-success">
														<span>+3.3%</span> <i data-feather="arrow-up"
															class="icon-sm mb-1"></i>
													</p> -->
												</div>
											</div>
											<div class="col-6 col-md-12 col-xl-7">
												<div id="customersChart" class="mt-md-3 mt-xl-0"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<div
											class="d-flex justify-content-between align-items-baseline">
											<h6 class="card-title mb-0">Reports</h6>
											<div class="dropdown mb-2"></div>
										</div>
										<div class="row">
											<div class="col-6 col-md-12 col-xl-5">
												<h3 class="mb-2">${reportCount}</h3>
												<div class="d-flex align-items-baseline">
													<!-- <p class="text-danger">
														<span>-2.8%</span> <i data-feather="arrow-down"
															class="icon-sm mb-1"></i>
													</p> -->
												</div>
											</div>
											<div class="col-6 col-md-12 col-xl-7">
												<div id="ordersChart" class="mt-md-3 mt-xl-0"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4 grid-margin stretch-card"></div>
						</div>
					</div>
				</div>

				<!-- row -->
				<div class="mb-5">
					<div class="row">

						<div class="col-6">
							<div class="card">
								<div class="card-body">
									<h6 class="card-title">Report Types Pie chart</h6>
									<canvas id="chartjsPie"></canvas>

								</div>
							</div>
						</div>

						<div class="col-6">
							<div class="card">
								<div class="card-body">
									<h6 class="card-title">Report Types Bar chart</h6>
									<canvas id="chartjsBar"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="" style="width: 70rem;">
						<div class="card">
							<div class="card-body">
								<h6 class="card-title">Patients-Reports Bar chart</h6>
								<canvas id="chartjsPatientReportsBar"></canvas>
							</div>
						</div>
					</div>

				</div>
				<!-- row -->
			</div>

			<!-- partial:partials/_footer.html -->
			<jsp:include page="footer.jsp"></jsp:include>
			<!-- partial -->
		</div>
	</div>

	<!-- core:js -->
	<script src="<%=request.getContextPath()%>/adminresources/js/core.js"></script>
	<!-- endinject -->

	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/bootstrap-maxlength.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/inputmask.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/select2.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/typeahead.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/tags-input.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/dropzone.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/dropify.js"></script>
	<script src="<%=request.getContextPath()%>/adminresources/js/pickr.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/flatpickr.js"></script>
	<!-- End custom js for this page -->

	<!-- Plugin js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/apexcharts.min.js"></script>
	<!-- End plugin js for this page -->

	<!-- inject:js -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/feather.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/template.js"></script>

	<script
		src="<%=request.getContextPath()%>/adminresources/js/Chart.min.js"></script>
	<%--  <script
		src="<%=request.getContextPath()%>/adminresources/js/chartjs-light.js"></script>  --%>
	<!-- endinject -->

	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/dashboard-light.js"></script>
	<!-- End custom js for this page -->

	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/doctorIndexChart.js"></script>
	<!-- End custom js for this page -->
</body>
</html>
