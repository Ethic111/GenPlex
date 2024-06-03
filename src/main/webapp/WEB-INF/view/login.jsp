<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
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

<title>GenPlex - Login</title>

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

<!-- <link rel="shortcut icon" href="adminresources/images/favicon.png" /> -->
<link rel="shortcut icon" href="/adminresources/images/genplexLOGO.png" />
</head>
<body>
	<div class="main-wrapper">
		<div class="page-wrapper full-page">
			<div
				class="page-content d-flex align-items-center justify-content-center">

				<div class="row w-100 mx-0 auth-page">
					<div class="col-md-8 col-xl-6 mx-auto">
						<div class="card">
							<div class="row" style="height: 448px;">
								<div class="col-md-4 pe-md-0">
									<div class="auth-side-wrapper"></div>
								</div>
								<div class="col-md-8 ps-md-0">
									<div class="auth-form-wrapper px-4 py-5">
										<h3 class="text-muted fw-normal mb-4">Login</h3>
										<form method="post" action="j_spring_security_check"
											id="loginForm">
											<div class="mb-3">
												<label for="username" class="form-label">UserName:<i
													class="text-danger">*</i>
												</label> <input type="text" class="form-control" id="userEmail"
													placeholder="Username" name="username" />
											</div>
											<div class="mb-3">
												<label for="password" class="form-label"> Password<i
													class="text-danger">*</i>
												</label> <input type="password" class="form-control"
													id="userPassword" autocomplete="current-password"
													placeholder="Password" name="password" />
											</div>
											<div>
												<input type="submit"
													class="btn btn-outline-primary btn-icon-text mb-2 mb-md-0"
													value="Login">
											</div>
										</form>
										<h6 class="d-block mt-3 mb-2 text-muted">Not Registered
											yet?</h6>
										<div class="">
											<a href="registerDoctor" class="mt-3 text-muted">Doctor
												Registration</a> <span class="mt-3 text-muted">||</span> <a
												href="registerPatient" class="mt-3 text-muted">Patient
												Registration</a>
										</div>
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

	<script src="adminresources/js/custom/login.js"></script>


	<!-- End custom js for this page -->


</body>
</html>