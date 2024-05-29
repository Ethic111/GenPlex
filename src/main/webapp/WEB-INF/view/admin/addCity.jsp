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

<title>Admin | Add City</title>

<!-- Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
	rel="stylesheet" />
<!-- End fonts -->

<!-- core:css -->
<link rel="stylesheet" href="adminresources/css/core.css" />
<!-- endinject -->

<!-- Plugin css for this page -->
<link rel="stylesheet" href="adminresources/css/select2.min.css" />
<link rel="stylesheet"
	href="adminresources/css/jquery.tagsinput.min.css" />
<link rel="stylesheet" href="adminresources/css/dropzone.min.css" />
<link rel="stylesheet" href="adminresources/css/dropify.min.css" />
<link rel="stylesheet" href="adminresources/css/classic.min.css" />
<link rel="stylesheet" href="adminresources/css/font-awesome.min.css" />
<link rel="stylesheet" href="adminresources/css/flatpickr.min.css" />
<!-- End plugin css for this page -->

<!-- inject:css -->
<link rel="stylesheet"
	href="adminresources/css/materialdesignicons.min.css">
<link rel="stylesheet" href="adminresources/css/flag-icon.min.css" />
<!-- endinject -->

<!-- Layout styles -->
<link rel="stylesheet" href="adminresources/css/style.min.css" />
<!-- End layout styles -->

<link rel="shortcut icon" href="adminresources/images/favicon.png" />
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
					<h3 class="page-title">Add City</h3>
				</div>
				<nav class="page-breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Manage City</a></li>
						<li class="breadcrumb-item active" aria-current="page">Add
							City</li>
					</ol>
				</nav>

				<div class="row">
					<div class="col-lg-12 grid-margin stretch-card">
						<div class="card">
							<div class="card-body">

								<f:form action="insertCity" method="post"
									modelAttribute="CityVo" id="addCityForm">
									<div>
										<div class="mb-3">
											<label for="statename" class="form-label">State Name<i
												class="text-danger">*</i></label>
											<f:select class="form-select" name="stateName"
												id="state_select" path="statevo.id">
												<f:option value="-1" label="Select your State" />
												<c:forEach items="${stateList}" var="i" varStatus="j">
													<f:option value="${i.id}">${i.stateName}</f:option>
												</c:forEach>
											</f:select>
										</div>
										<div class="mb-3">
											<label for="cityname" class="form-label">City Name<i
												class="text-danger">*</i></label>
											<f:input id="cityname" class="form-control" name="cityName"
												type="text" path="cityName" />
										</div>
										<div class="mb-3">
											<label for="citydesc" class="form-label">Description<i
												class="text-danger">*</i></label>
											<f:textarea cols="3" rows="3" class="form-control"
												name="cityDescription" id="citydesc" path="cityDescription"></f:textarea>
										</div>
										<f:hidden path="id" />


										<input class="btn btn-primary" type="submit" value="Submit" />
									</div>
								</f:form>

							</div>
						</div>
					</div>

				</div>

			</div>
		</div>
		<!-- partial:partials/_footer.html -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- partial -->
	</div>

	<!-- core:js -->
	<script src="adminresources/js/core.js"></script>
	<!-- endinject -->

	<!-- Plugin js for this page -->
	<script src="adminresources/js/jquery.validate.min.js"></script>
	<script src="adminresources/js/bootstrap-maxlength.min.js"></script>
	<script src="adminresources/js/jquery.inputmask.min.js"></script>
	<script src="adminresources/js/select2.min.js"></script>
	<script src="adminresources/js/typeahead.bundle.min.js"></script>
	<script src="adminresources/js/jquery.tagsinput.min.js"></script>
	<script src="adminresources/js/dropzone.min.js"></script>
	<script src="adminresources/js/dropify.min.js"></script>
	<script src="adminresources/js/pickr.min.js"></script>
	<script src="adminresources/js/moment.min.js"></script>
	<script src="adminresources/js/flatpickr.min.js"></script>
	<!-- End plugin js for this page -->

	<!-- inject:js -->
	<script src="adminresources/js/feather.min.js"></script>
	<script src="adminresources/js/template.js"></script>
	<!-- endinject -->

	<!-- Custom js for this page -->
	<script src="adminresources/js/bootstrap-maxlength.js"></script>
	<script src="adminresources/js/inputmask.js"></script>
	<script src="adminresources/js/select2.js"></script>
	<script src="adminresources/js/typeahead.js"></script>
	<script src="adminresources/js/tags-input.js"></script>
	<script src="adminresources/js/dropzone.js"></script>
	<script src="adminresources/js/dropify.js"></script>
	<script src="adminresources/js/pickr.js"></script>
	<script src="adminresources/js/flatpickr.js"></script>
	<!-- End custom js for this page -->

	<!-- Custom add state form validation -->
	<script src="adminresources/js/custom/city.js"></script>
</body>
</html>
