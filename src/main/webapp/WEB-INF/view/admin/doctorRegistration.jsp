<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="description"
	content="Responsive HTML Admin Dashboard Template based on Bootstrap 5">
<meta name="author" content="NobleUI">
<meta name="keywords"
	content="nobleui, bootstrap, bootstrap 5, bootstrap5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

<title>Doctor - Registration</title>

<!-- Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
	rel="stylesheet">
<!-- End fonts -->

<!-- core:css -->
<link rel="stylesheet" href="adminresources/css/core.css">
<!-- endinject -->

<!-- Plugin css for this page -->
<!-- End plugin css for this page -->

<!-- inject:css -->
<link rel="stylesheet" href="adminresources/css/iconfont.css">
<link rel="stylesheet" href="adminresources/css/flag-icon.min.css">
<!-- endinject -->

<!-- Layout styles -->
<link rel="stylesheet" href="adminresources/css/style.min.css">
<!-- End layout styles -->

<link rel="shortcut icon" href="adminresources/images/favicon.png" />
</head>
<body>
	<div class="main-wrapper">
		<div class="page-wrapper full-page">
			<div
				class="page-content d-flex align-items-center justify-content-center">

				<div class="row w-100 mx-0 auth-page">
					<div class="col-md-8 col-xl-6 mx-auto">
						<div class="card">
							<div class="row">
								<div class="col-md-4 pe-md-0">
									<div class="auth-side-wrapper"></div>
								</div>
								<div class="col-md-8 ps-md-0">
									<div class="auth-form-wrapper px-4 py-5">
										<a href="#" class="noble-ui-logo d-block mb-2">Gen<span>Plex</span></a>
										<h5 class="text-muted fw-normal mb-4">Doctor Registration</h5>
										<f:form action="insertRequestedDoctor" method="post"
											modelAttribute="RequestedDoctorVo"
											id="doctorRegistrationForm">
											<div>
												<div class="mb-3">
													<label for="doctorname" class="form-label">Full
														Name<i class="text-danger">*</i>
													</label>
													<f:input id="doctorname" class="form-control"
														name="doctorName" type="text" path="doctorName" />
												</div>
												<div class="mb-3">
													<label for="doctoremail" class="form-label">
														Email<i class="text-danger">*</i>
													</label>
													<f:input id="doctoremail" class="form-control"
														name="doctorEmail" type="email" path="email" />
												</div>
												<div class="mb-3">
													<label for="statename" class="form-label">State
														Name<i class="text-danger">*</i>
													</label>
													<f:select class="form-select" name="stateId"
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
													<f:select class="form-select" name="cityId"
														id="city_select" path="cityvo.id">
														<f:option value="-1" label="Select your City" />
															<c:forEach items="${cityList}" var="i" varStatus="j">
															<f:option value="${i.id}">${i.cityName}</f:option>
														</c:forEach>
													</f:select>
												</div>

												<div class="mb-3">
													<label for="doctordegree" class="form-label">Degree
														<i class="text-danger">*</i>
													</label>
													<f:select class="form-select" name="degreeId"
														id="doctordegree" path="degreevo.id">
														<f:option value="-1" label="Select your Degree" />
															<c:forEach items="${degreeList}" var="i" varStatus="j">
																<f:option value="${i.id}">${i.degreeName}</f:option>
															</c:forEach>
													</f:select>
												</div>
												<%-- <div class="mb-3">
													<label for="doctorcertification" class="form-label">Certification<i
														class="text-danger">*</i></label>
													<f:input id="doctorcertification" class="form-control"
														name="doctorCertification" type="file"
														path="certification"  />
												</div> 
 												--%>
												<f:hidden path="id" />

											</div>
											<div>

												<button type="submit"
													class="btn btn-outline-primary btn-icon-text mb-2 mb-md-0">

													Register</button>
											</div>
											<a href="" class="d-block mt-3 text-muted">Already a
												user? Sign in</a>
										</f:form>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- core:js -->
	<script src="adminresources/js/core.js"></script>
	<!-- endinject -->

	<!-- Plugin js for this page -->
	
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
		
	<!-- End plugin js for this page -->

	<!-- inject:js -->
	<script src="adminresources/js/feather.min.js"></script>
	<script src="adminresources/js/template.js"></script>
	<!-- endinject -->

	<!-- Custom js for this page -->
	<script src="adminresources/js/custom/doctorRegistration.js"></script>
	<!-- End custom js for this page -->
	

</body>
</html>