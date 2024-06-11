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
									<img
										src="<%=request.getContextPath()%>/adminresources/images/doc1.avif"
										class="rounded-top" alt="profile cover" />
								</figure>
								<div
									class="d-flex justify-content-between align-items-center position-absolute top-90 w-100 px-2 px-md-4 mt-n4">
									<div>
										<img class="wd-70 rounded-circle"
											src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
											alt="profile" /> <span class="h4 ms-3 text-dark">${doctorvo.doctorName }</span>
									</div>
									<!-- <div class="d-none d-md-block">
											<button class="btn btn-primary btn-icon-text">
												<i data-feather="edit" class="btn-icon-prepend"></i> Edit
												profile
											</button>
										</div> -->
								</div>
							</div>
							<!-- <div class="d-flex justify-content-center p-3 rounded-bottom">
									<ul class="d-flex align-items-center m-0 p-0">
										<li class="d-flex align-items-center active"><i
											class="me-1 icon-md text-primary" data-feather="columns"></i>
											<a class="pt-1px d-none d-md-block text-primary" href="#">Timeline</a>
										</li>
										<li class="ms-3 ps-3 border-start d-flex align-items-center">
											<i class="me-1 icon-md" data-feather="user"></i> <a
											class="pt-1px d-none d-md-block text-body" href="#">About</a>
										</li>
										<li class="ms-3 ps-3 border-start d-flex align-items-center">
											<i class="me-1 icon-md" data-feather="users"></i> <a
											class="pt-1px d-none d-md-block text-body" href="#">Friends
												<span class="text-muted tx-12">3,765</span>
										</a>
										</li>
										<li class="ms-3 ps-3 border-start d-flex align-items-center">
											<i class="me-1 icon-md" data-feather="image"></i> <a
											class="pt-1px d-none d-md-block text-body" href="#">Photos</a>
										</li>
										<li class="ms-3 ps-3 border-start d-flex align-items-center">
											<i class="me-1 icon-md" data-feather="video"></i> <a
											class="pt-1px d-none d-md-block text-body" href="#">Videos</a>
										</li>
									</ul>
								</div> -->
						</div>
					</div>
				</div>
				<div class="row profile-body">
					<!-- left wrapper start -->
					<div class="d-none d-md-block col-md-4 col-xl-3 left-wrapper">
						<div class="card rounded">
							<div class="card-body">
								<div
									class="d-flex align-items-center justify-content-between mb-2">
									<h6 class="card-title mb-0">About</h6>
									<!-- <div class="dropdown">
											<a type="button" id="dropdownMenuButton"
												data-bs-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false"> <i
												class="icon-lg text-muted pb-3px"
												data-feather="more-horizontal"></i>
											</a>
											<div class="dropdown-menu"
												aria-labelledby="dropdownMenuButton">
												<a class="dropdown-item d-flex align-items-center"
													href="javascript:;"><i data-feather="edit-2"
													class="icon-sm me-2"></i> <span class="">Edit</span></a> <a
													class="dropdown-item d-flex align-items-center"
													href="javascript:;"><i data-feather="git-branch"
													class="icon-sm me-2"></i> <span class="">Update</span></a> <a
													class="dropdown-item d-flex align-items-center"
													href="javascript:;"><i data-feather="eye"
													class="icon-sm me-2"></i> <span class="">View all</span></a>
											</div>
										</div> -->
								</div>
								<p>Hi! I'm Amiah the Senior UI Designer at NobleUI. We hope
									you enjoy the design and quality of Social.</p>
								<div class="mt-3">
									<label class="tx-11 fw-bolder mb-0 text-uppercase">Joined:</label>
									<p class="text-muted">November 15, 2015</p>
								</div>
								<div class="mt-3">
									<label class="tx-11 fw-bolder mb-0 text-uppercase">Lives:</label>
									<p class="text-muted">New York, USA</p>
								</div>
								<div class="mt-3">
									<label class="tx-11 fw-bolder mb-0 text-uppercase">Email:</label>
									<p class="text-muted">${doctorvo.email }</p>
								</div>
								<div class="mt-3">
									<label class="tx-11 fw-bolder mb-0 text-uppercase">Website:</label>
									<p class="text-muted">www.nobleui.com</p>
								</div>
								<div class="mt-3 d-flex social-links">
									<a href="javascript:;" class="btn btn-icon border btn-xs me-2">
										<i data-feather="github"></i>
									</a> <a href="javascript:;" class="btn btn-icon border btn-xs me-2">
										<i data-feather="twitter"></i>
									</a> <a href="javascript:;" class="btn btn-icon border btn-xs me-2">
										<i data-feather="instagram"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
					<!-- left wrapper end -->
					<!-- middle wrapper start -->
					<div class="col-md-8 col-xl-5 middle-wrapper">
						<div class="row">
							<div class="col-md-12 grid-margin">
								<div class="card rounded">
									<div class="card-header">
										<div class="d-flex align-items-center justify-content-between">
											<div class="d-flex align-items-center">
												<img class="img-xs rounded-circle"
													src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
													alt="" />
												<div class="ms-2">
													<p>Mike Popescu</p>
													<p class="tx-11 text-muted">1 min ago</p>
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
										<p class="mb-3 tx-14">Degree Certificate:</p>
										<img class="img-fluid" src="${doctorvo.certificatePath}"
											alt="" />
									</div>

								</div>
							</div>
							<div class="col-md-12 grid-margin">
								<div class="card rounded">
									<div class="card-header">
										<div class="d-flex align-items-center justify-content-between">
											<div class="d-flex align-items-center">
												<img class="img-xs rounded-circle"
													src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
													alt="" />
												<div class="ms-2">
													<p>Mike Popescu</p>
													<p class="tx-11 text-muted">1 min ago</p>
												</div>
											</div>
											<div>
												<!-- placing a downoad button here  -->
												<a href="#"><i class="mdi mdi-download"
													style="color: blue; font-size: 1.3rem;"></i> </a>
											</div>

										</div>
									</div>
									<div class="card-body">
										<p class="mb-3 tx-14">Address Proof:</p>
										<img class="img-fluid" src="${doctorvo.addressProofPath}"
											alt="" />
									</div>

								</div>
							</div>
							<div class="col-md-12 grid-margin">
								<div class="card rounded">
									<div class="card-header">
										<div class="d-flex align-items-center justify-content-between">
											<div class="d-flex align-items-center">
												<img class="img-xs rounded-circle"
													src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
													alt="" />
												<div class="ms-2">
													<p>Mike Popescu</p>
													<p class="tx-11 text-muted">1 min ago</p>
												</div>
											</div>
											<div>
												<!-- placing a downoad button here  -->
												<a href="#"><i class="mdi mdi-download"
													style="color: blue; font-size: 1.3rem;"></i> </a>
											</div>

										</div>
									</div>
									<div class="card-body">
										<p class="mb-3 tx-14">Government ID:</p>
										<img class="img-fluid" src="${doctorvo.governmentIdPath}"
											alt="" />
									</div>

								</div>
							</div>
						</div>
					</div>
					<!-- middle wrapper end -->
					<!-- right wrapper start -->
					<div class="d-none d-xl-block col-xl-3">
						<div class="row">
							<div class="col-md-12 grid-margin">
								<div class="card rounded">
									<div class="card-body">
										<h6 class="card-title">latest photos</h6>
										<div class="row ms-0 me-0">
											<a href="javascript:;" class="col-md-4 ps-1 pe-1">
												<figure class="mb-2">
													<img class="img-fluid rounded"
														src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
														alt="" />
												</figure>
											</a> <a href="javascript:;" class="col-md-4 ps-1 pe-1">
												<figure class="mb-2">
													<img class="img-fluid rounded"
														src="<%=request.getContextPath()%>/adminresources/images/face2.jpg"
														alt="" />
												</figure>
											</a> <a href="javascript:;" class="col-md-4 ps-1 pe-1">
												<figure class="mb-2">
													<img class="img-fluid rounded"
														src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
														alt="" />
												</figure>
											</a> <a href="javascript:;" class="col-md-4 ps-1 pe-1">
												<figure class="mb-2">
													<img class="img-fluid rounded"
														src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
														alt="" />
												</figure>
											</a> <a href="javascript:;" class="col-md-4 ps-1 pe-1">
												<figure class="mb-2">
													<img class="img-fluid rounded"
														src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
														alt="" />
												</figure>
											</a> <a href="javascript:;" class="col-md-4 ps-1 pe-1">
												<figure class="mb-2">
													<img class="img-fluid rounded"
														src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
														alt="" />
												</figure>
											</a> <a href="javascript:;" class="col-md-4 ps-1 pe-1">
												<figure class="mb-0">
													<img class="img-fluid rounded"
														src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
														alt="" />
												</figure>
											</a> <a href="javascript:;" class="col-md-4 ps-1 pe-1">
												<figure class="mb-0">
													<img class="img-fluid rounded"
														src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
														alt="" />
												</figure>
											</a> <a href="javascript:;" class="col-md-4 ps-1 pe-1">
												<figure class="mb-0">
													<img class="img-fluid rounded"
														src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
														alt="" />
												</figure>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12 grid-margin">
								<div class="card rounded">
									<div class="card-body">
										<h6 class="card-title">suggestions for you</h6>
										<div
											class="d-flex justify-content-between mb-2 pb-2 border-bottom">
											<div class="d-flex align-items-center hover-pointer">
												<img class="img-xs rounded-circle"
													src="<%=request.getContextPath()%>/adminresources/images/face2.jpg"
													alt="" />
												<div class="ms-2">
													<p>Mike Popescu</p>
													<p class="tx-11 text-muted">12 Mutual Friends</p>
												</div>
											</div>
											<button class="btn btn-icon border-0">
												<i data-feather="user-plus"></i>
											</button>
										</div>
										<div
											class="d-flex justify-content-between mb-2 pb-2 border-bottom">
											<div class="d-flex align-items-center hover-pointer">
												<img class="img-xs rounded-circle"
													src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
													alt="" />
												<div class="ms-2">
													<p>Mike Popescu</p>
													<p class="tx-11 text-muted">12 Mutual Friends</p>
												</div>
											</div>
											<button class="btn btn-icon border-0">
												<i data-feather="user-plus"></i>
											</button>
										</div>
										<div
											class="d-flex justify-content-between mb-2 pb-2 border-bottom">
											<div class="d-flex align-items-center hover-pointer">
												<img class="img-xs rounded-circle"
													src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
													alt="" />
												<div class="ms-2">
													<p>Mike Popescu</p>
													<p class="tx-11 text-muted">12 Mutual Friends</p>
												</div>
											</div>
											<button class="btn btn-icon border-0">
												<i data-feather="user-plus"></i>
											</button>
										</div>
										<div
											class="d-flex justify-content-between mb-2 pb-2 border-bottom">
											<div class="d-flex align-items-center hover-pointer">
												<img class="img-xs rounded-circle"
													src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
													alt="" />
												<div class="ms-2">
													<p>Mike Popescu</p>
													<p class="tx-11 text-muted">12 Mutual Friends</p>
												</div>
											</div>
											<button class="btn btn-icon border-0">
												<i data-feather="user-plus"></i>
											</button>
										</div>
										<div
											class="d-flex justify-content-between mb-2 pb-2 border-bottom">
											<div class="d-flex align-items-center hover-pointer">
												<img class="img-xs rounded-circle"
													src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
													alt="" />
												<div class="ms-2">
													<p>Mike Popescu</p>
													<p class="tx-11 text-muted">12 Mutual Friends</p>
												</div>
											</div>
											<button class="btn btn-icon border-0">
												<i data-feather="user-plus"></i>
											</button>
										</div>
										<div class="d-flex justify-content-between">
											<div class="d-flex align-items-center hover-pointer">
												<img class="img-xs rounded-circle"
													src="<%=request.getContextPath()%>/adminresources/images/face3.jpg"
													alt="" />
												<div class="ms-2">
													<p>Mike Popescu</p>
													<p class="tx-11 text-muted">12 Mutual Friends</p>
												</div>
											</div>
											<button class="btn btn-icon border-0">
												<i data-feather="user-plus"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- right wrapper end -->
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
	<!-- End custom js for this page -->
</body>
</html>
