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

<title>Admin | Doctors</title>


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

<!-- Plugin css for this page -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/sweetalert2.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/select2.min.css" />
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

<!-- Layout styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/adminresources/css/style.min.css">
<!-- End layout styles -->

<%-- <link rel="shortcut icon"
	href="<%=request.getContextPath()%>/adminresources/images/favicon.png" /> --%>
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
				<div class="d-flex justify-content-between">
					<nav class="page-breadcrumb">
						<div>
							<div class="row mb-3">
								<h3 class="page-title">Doctors</h3>
							</div>
							<nav class="page-breadcrumb">

								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="doctors">Manage
											Doctor</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										Doctors</li>
								</ol>
							</nav>
						</div>
					</nav>

					<div>
						<div class="d-flex justify-content-between">
							<div>
								<label for="reviewStatus" class="form-label"></label> <select
									class="form-select" name="reviewStatus"
									id="reviewstatus_select" style="cursor: pointer;">
									<option value="NOT_REVIEWED">New Requests</option>
									<option value="ACCEPTED">Active</option>
									<option value="REJECTED">Rejected</option>
								</select>
							</div>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-md-12 grid-margin stretch-card">
						<div class="card">
							<div class="card-body">

								<div class="table-responsive">
									<table id="dataTableExample" class="table">
										<thead>
											<tr>
												<th>#</th>
												<th>Doctor Name</th>
												<th>State Name</th>
												<th>City Name</th>
												<th>Degree</th>
												<!-- <th>Certification</th>
												<th>Address Proof</th>
												<th>Government ID</th> -->
												<th>Action</th>

											</tr>
										</thead>
										<tbody id="data-table-body">
											<c:forEach var="i" items="${reqDoctorList}" varStatus="j">
												<tr>
													<td>${j.count}</td>
													<td>${i.doctorName}</td>
													<td>${i.statevo.stateName}</td>
													<td>${i.cityvo.cityName}</td>
													<td>${i.degreevo.degreeName}</td>
													<c:if test="${i.reviewStatus == 'ACCEPTED'}">
														<td><a class="download-link" href="#"
															data-file-path="${i.certificatePath}"><i
																class="mdi mdi-download"
																style="color: blue; font-size: 1.5rem;"></i></a> <a
															class="download-link" href="#"
															data-file-path="${i.addressProofPath}"><i
																class="mdi mdi-download"
																style="color: blue; font-size: 1.5rem;"></i></a> <a
															class="download-link" href="#"
															data-file-path="${i.governmentIdPath}"><i
																class="mdi mdi-download"
																style="color: blue; font-size: 1.5rem;"></i></a></td>
													</c:if>
													<td><c:choose>
															<c:when test="${i.reviewStatus == 'ACCEPTED'}">
																<a href="deleteDoctor?id=${i.id}"><i
																	class="mdi mdi-delete-sweep"
																	style="color: red; font-size: 1.5rem;"></i></a>
															</c:when>
															<c:otherwise>
																<%-- <a href="doctorAccepted?id=${i.id}">
																	<button class="btn btn-success">Accept</button>
																</a> --%>

																<button class="btn btn-success accept-btn"
																	id="submitAcception" onclick="acceptDoctor(${i.id})">Accept</button>

																<!-- doctorRejected?id=${i.id} -->

																<button class="btn btn-danger reject-btn"
																	onclick="showRejectModal(${i.id})"
																	data-bs-toggle="modal" data-bs-target="#exampleModal">Reject</button>


															</c:otherwise>
														</c:choose></td>
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="modal fade" id="exampleModal" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title text-danger" id="exampleModalLabel">Are
														you sure you want to Reject ?</h4>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">
													<form id="rejectionForm">
														<div class="form-group">
															<label for="rejectionReason"><h5>
																	Reason For Rejection:<i class="text-danger">*</i>
																</h5></label>
															<textarea id="rejectionReason" class="form-control"
																name="rejectionReason"></textarea>
														</div>
													</form>
													<div id="errorMessage" class="text-danger mt-2"></div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Close</button>
													<button type="button" class="btn btn-primary"
														id="submitRejection">Reject</button>
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
	<!-- endinject -->

	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/data-table.js"></script>
	<!-- End custom js for this page -->

	<!-- Plugin js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/sweetalert2.min.js"></script>
	<!-- End plugin js for this page -->

	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/select2.js"></script>
	<!-- End custom js for this page -->

	<!-- Plugin js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/jquery.validate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/jquery.inputmask.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/select2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/typeahead.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/jquery.tagsinput.min.js"></script>

	<!-- End plugin js for this page -->


	<!-- Custom javascript for filterting -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/filterDoctors.js"></script>
</body>
</html>
