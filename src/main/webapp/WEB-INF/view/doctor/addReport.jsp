<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
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

<title>Doctor | Add Report</title>

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
	href="<%=request.getContextPath()%>/adminresources/css/jquery.tagsinput.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/dropzone.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/dropify.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/classic.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/flatpickr.min.css" />
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

				<div class="row mb-3">
					<h3 class="page-title">Add Report</h3>
				</div>
				<nav class="page-breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Manage Reports</a></li>
						<li class="breadcrumb-item active" aria-current="page">Add
							Report</li>
					</ol>
				</nav>
				<div class="row">
					<div class="col-lg-12 grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<f:form id="addReportForm" method="post" action="saveReport"
									enctype="multipart/form-data" modelAttribute="ReportVo">
									<div class="row">
										<div class="col-6 mb-3">
											<label for="reportname" class="form-label">Report
												Type<i class="text-danger">*</i>
											</label>
											<f:select class="form-select" name="reportType"
												id="reportType_select" path="reporttypevo.id">
												<f:option value="-1" label="Select a Report Type" />
												<c:forEach items="${reportTypeList}" var="i" varStatus="j">
													<f:option value="${i.id}">${i.reportTypeName}</f:option>
												</c:forEach>
											</f:select>
											<f:errors path="reporttypevo.id" cssClass="text-danger" />
										</div>
										<!-- <div class="input-group flatpickr" id="flatpickr-date">
											<input type="text" class="form-control"
												placeholder="Select date" data-input/>
											<span class="input-group-text input-group-addon" data-toggle><i
												data-feather="calendar"></i></span>
										</div> -->
										<div class="col-6 mb-3">
											<label for="reportDate" class="form-label">Date<i
												class="text-danger">*</i></label>

											<f:input id="reportDate" class="form-control" name="date"
												type="date" path="date" />
											<f:errors path="date" cssClass="text-danger" />

										</div>
										<div class="col-6 mb-3">
											<label for="statename" class="form-label">State Name<i
												class="text-danger">*</i></label>
											<f:select class="form-select" name="stateId"
												id="state_select" path="statevo.id">
												<f:option value="-1" label="Select State" />
												<c:forEach items="${stateList}" var="i" varStatus="j">
													<f:option value="${i.id}">${i.stateName}</f:option>
												</c:forEach>
											</f:select>
											<f:errors path="statevo.id" cssClass="text-danger" />
										</div>
										<div class="col-6 mb-3">
											<label for="cityname" class="form-label">City Name<i
												class="text-danger">*</i></label>
											<f:select class="form-select" name="cityId" id="city_select"
												path="cityvo.id">
												<f:option value="-1" label="Select City" />
												<c:forEach items="${cityList}" var="i" varStatus="j">
													<f:option value="${i.id}">${i.cityName}</f:option>
												</c:forEach>
											</f:select>
											<f:errors path="cityvo.id" cssClass="text-danger" />
										</div>
										<div class="col-6 mb-3">
                                            <label for="patientname" class="form-label">Patient Username<i class="text-danger">*</i></label>
                                            <f:select class="form-select" name="patientId"
                                                id="patient_select" path="patientvo.id">
                                                <f:option value="-1" label="Select Patient" />
                                                <c:forEach items="${patientList}" var="i" varStatus="j">
                                                    <f:option value="${i.id}">${i.email}</f:option>
                                                </c:forEach>
                                            </f:select>
                                            <f:errors path="patientvo.id" cssClass="text-danger" />
                                        </div>
										<div class="col-6 mb-3">
											<label for="reportfile" class="form-label">Report
												File<i class="text-danger">*</i>
											</label> <input type="file" name="reportFile" class="form-control">

										</div>

										<div class="mb-3">
											<label for="reportsummary" class="form-label">Report
												Summary<i class="text-danger">*</i>
											</label>
											<f:textarea cols="3" rows="3" class="form-control"
												name="summary" id="reportSummary" path="summary"></f:textarea>
											<f:errors path="summary" cssClass="text-danger" />
										</div>
										<f:hidden path="id" />


									</div>
									<input class="btn btn-primary" type="submit" value="Submit" />
								</f:form>

							</div>
						</div>
					</div>

				</div>

			</div>
			<!-- partial:partials/_footer.html -->
			<jsp:include page="footer.jsp"></jsp:include>
			<!-- partial -->
		</div>

	</div>

	<!-- core:js -->
	<script src="<%=request.getContextPath()%>/adminresources/js/core.js"></script>
	<!-- endinject -->

	<!-- Plugin js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/jquery.validate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/bootstrap-maxlength.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/jquery.inputmask.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/select2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/typeahead.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/jquery.tagsinput.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/dropzone.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/dropify.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/pickr.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/moment.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/flatpickr.min.js"></script>
	<!-- End plugin js for this page -->

	<!-- inject:js -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/feather.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/template.js"></script>
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

	<!-- Custom add state form validation -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/addReport.js"></script>
</body>
</html>
