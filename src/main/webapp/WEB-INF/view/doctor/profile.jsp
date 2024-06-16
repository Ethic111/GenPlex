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

<title>Doctor - Profile</title>

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
				<div class="row">
					<div class="col-12 grid-margin">
						<div class="card">
							<div class="position-relative">
								<figure
									class="overflow-hidden mb-0 d-flex justify-content-center">
									<%-- <img
										src="<%=request.getContextPath()%>/adminresources/images/doc1.avif"
										class="rounded-top" alt="profile cover" /> --%>

									<img
										src="<%=request.getContextPath()%>/adminresources/images/doctorCoverImage1.jpg"
										class="rounded-top" alt="profile cover"
										style="width: 85rem; height: 20rem;" />
									<!-- does not support this avif photo here -->
								</figure>
								<div
									class="d-flex justify-content-between align-items-center position-absolute top-90 w-100 px-2 px-md-4 mt-n4">
									<div>
										<img class="wd-70 rounded-circle"
											src="<%=request.getContextPath()%>/adminresources/images/doctorProfileIcon.jpg"
											alt="profile" /> <span class="h4 ms-3 text-light">${doctorvo.doctorName}</span>
									</div>
									<div class="d-none d-md-block">
										<button class="btn btn-primary btn-icon-text">
											<i class="mdi mdi-email-variant mr-2"></i> ${doctorvo.email}
										</button>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class="row profile-body">
					<!-- left wrapper start -->
					<div class="col-4">
						<div class="card rounded">
							<div class="card-header">
								<div class="d-flex align-items-center justify-content-between">
									<div class="d-flex align-items-center">
										<img class="img-xs rounded-circle"
											src="<%=request.getContextPath()%>/adminresources/images/doctorProfileIcon.jpg"
											alt="" />
										<div class="ms-2">
											<p>${doctorvo.doctorName}</p>
											<p class="tx-11 text-muted">Degree Certificate:</p>
										</div>
									</div>
									<div>
										<!-- placing a downoad button here  -->
										<a class="download-link" href="#"
											data-file-path="${doctorvo.certificatePath}"><i
											class="mdi mdi-download"
											style="color: blue; font-size: 1.3rem;"></i></a>
									</div>

								</div>
							</div>
							<div class="card-body">
								<p class="mb-3 tx-14"></p>
								<img class="img-fluid" src="${doctorvo.certificatePath}" alt="" />
							</div>
						</div>
					</div>
					<div class="col-4">
						<div class="card rounded">
							<div class="card-header">
								<div class="d-flex align-items-center justify-content-between">
									<div class="d-flex align-items-center">
										<img class="img-xs rounded-circle"
											src="<%=request.getContextPath()%>/adminresources/images/doctorProfileIcon.jpg"
											alt="" />
										<div class="ms-2">
											<p>${doctorvo.doctorName}</p>
											<p class="tx-11 text-muted">Address Proof:</p>
										</div>
									</div>
									<div>
										<!-- placing a downoad button here  -->
										<a class="download-link" href="#"
											data-file-path="${doctorvo.addressProofPath}"><i
											class="mdi mdi-download"
											style="color: blue; font-size: 1.3rem;"></i></a>
									</div>

								</div>
							</div>
							<div class="card-body">
								<p class="mb-3 tx-14"></p>
								<img class="img-fluid" src="${doctorvo.addressProofPath}" alt="" />
							</div>
						</div>
					</div>
					<div class="col-4">
						<div class="card rounded">
							<div class="card-header">
								<div class="d-flex align-items-center justify-content-between">
									<div class="d-flex align-items-center">
										<img class="img-xs rounded-circle"
											src="<%=request.getContextPath()%>/adminresources/images/doctorProfileIcon.jpg"
											alt="" />
										<div class="ms-2">
											<p>${doctorvo.doctorName}</p>
											<p class="tx-11 text-muted">Government ID:</p>
										</div>
									</div>
									<div>
										<!-- placing a downoad button here  -->
										<a class="download-link" href="#"
											data-file-path="${doctorvo.governmentIdPath}"><i
											class="mdi mdi-download"
											style="color: blue; font-size: 1.3rem;"></i></a>
									</div>

								</div>
							</div>
							<div class="card-body">
								<p class="mb-3 tx-14"></p>
								<img class="img-fluid" src="${doctorvo.governmentIdPath}" alt="" />
							</div>
						</div>
					</div>
				</div>

			</div>

			<!-- partial:../../partials/_footer.html -->
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
	<%-- <script
			src="<%=request.getContextPath()%>/adminresources/js/dropzone.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/adminresources/js/dropify.min.js"></script> --%>
	<%-- <script
			src="<%=request.getContextPath()%>/adminresources/js/pickr.min.js"></script> --%>
	<%-- 	<script
			src="<%=request.getContextPath()%>/adminresources/js/moment.min.js"></script> --%>
	<%-- <script
			src="<%=request.getContextPath()%>/adminresources/js/flatpickr.min.js"></script> --%>
	<!-- End plugin js for this page -->

	<!-- inject:js -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/feather.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/template.js"></script>
	<!-- endinject -->

	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/form-validation.js"></script>
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
	<%-- <script
			src="<%=request.getContextPath()%>/adminresources/js/dropzone.js"></script> --%>
	<%-- <script
			src="<%=request.getContextPath()%>/adminresources/js/dropify.js"></script>
		<script src="<%=request.getContextPath()%>/adminresources/js/pickr.js"></script> --%>
	<%-- <script
			src="<%=request.getContextPath()%>/adminresources/js/flatpickr.js"></script> --%>
	<!-- End custom js for this page -->


	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/profile.js"></script>
	<!-- End custom js for this page -->
</body>
</html>
