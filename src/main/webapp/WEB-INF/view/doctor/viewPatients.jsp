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

<title>Doctor | Patients</title>

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
							<h3 class="page-title">Patients</h3>
						</div>



						<nav class="page-breadcrumb">

							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Manage Patients</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Patients</li>
							</ol>


						</nav>
					</div>
					<div class="col-3 mb-3">
						<select class="form-select" name="search_value" id="search_select">
							<option value="all">All City-State</option>
							<c:forEach items="${cityList}" var="i" varStatus="j">
								<option value="${i.id}">${i.cityName}</option>
							</c:forEach>
							<c:forEach items="${stateList}" var="x" varStatus="y">
								<option value="${x.id}">${x.stateName}</option>
							</c:forEach>
						</select>
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
												<th>Name</th>
												<th>Email</th>
												<th>State</th>
												<th>City</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${patientDoctorList}" var="i" varStatus="j">
												<tr>
													<td>${j.count}</td>
													<td><c:choose>
															<c:when test="${i.patientvo.patientName != null}">
                                                                ${i.patientvo.patientName}
                                                            </c:when>
															<c:otherwise>
                                                                -
                                                            </c:otherwise>
														</c:choose></td>
													<td>${i.patientvo.email}</td>
													<td>${i.patientvo.statevo.stateName}</td>
													<td>${i.patientvo.cityvo.cityName}</td>
													<td><a href="deletePatient?pid=${i.patientvo.id}"><i
															class="mdi mdi-delete-sweep"
															style="color: red; font-size: 1.5rem;"></i></a> <%-- <a
														href="editReportType?id=${i.id}"
														style="margin-left: 1rem;"><i
															class="mdi mdi-table-edit"
															style="color: blue; font-size: 1.5rem;"></i> </a> --%></td>
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
		src="<%=request.getContextPath()%>/adminresources/js/select2.min.js"></script>
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
	<script
		src="<%=request.getContextPath()%>/adminresources/js/select2.js"></script>
	<!-- End custom js for this page -->

	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/downloadFile.js"></script>
	<script
		src="<%=request.getContextPath()%>/adminresources/js/custom/doctorPatientFilter.js"></script>
	<!-- End custom js for this page -->
</body>
</html>
