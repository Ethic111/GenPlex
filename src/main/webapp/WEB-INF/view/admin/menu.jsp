<nav class="sidebar ">
	<div class="sidebar-header">
		<a href="#" class="sidebar-brand"> <!--  Gen<span>Plex</span> -->
			<img
			src="<%=request.getContextPath()%>/adminresources/images/GenplexImage.png"
			style="width: 168px; height: 36px; margin-left: -22px;" />
		</a>
		<div class="sidebar-toggler not-active">
			<span></span> <span></span> <span></span>
		</div>
	</div>
	<div class="sidebar-body ">
		<ul class="nav">

			<li class="nav-item mb-3"><a href="index" class="nav-link">
					<i class="link-icon menu-icon mdi mdi-home"></i> <span
					class="link-title"> Dashboard </span>
			</a></li>

			<li class="nav-item mb-3"><a href="states" class="nav-link">
					<i class="link-icon mdi mdi-map-marker-radius"></i> <span
					class="link-title">Manage State</span>
			</a></li>


			<li class="nav-item mb-3"><a href="cities" class="nav-link">
					<i class="link-icon mdi mdi-city"></i> <span class="link-title">Manage
						City</span>
			</a></li>


			<li class="nav-item mb-3"><a href="doctors" class="nav-link">
					<i class="link-icon mdi mdi-stethoscope"></i> <span
					class="link-title">Manage Doctor</span>
			</a></li>

			<li class="nav-item mb-3"><a href="patients"
				class="nav-link menu-link"> <i
					class="link-icon mdi mdi-account-multiple"></i> <span
					class="link-title">Manage Patients</span>
			</a></li>

			<li class="nav-item mb-3"><a href="degrees" class="nav-link">
					<i class="link-icon mdi mdi-certificate"></i> <span
					class="link-title">Manage Degree</span>
			</a></li>

			<li class="nav-item mb-3"><a href="reportTypes" class="nav-link">
					<i class="link-icon mdi mdi-file-multiple"></i> <span
					class="link-title">Manage Report Types</span>
			</a></li>

			<li class="nav-item mb-3"><a href="reports"
				class="nav-link menu-link"> <i
					class="link-icon mdi mdi-file-document"></i> <span
					class="link-title">Manage Reports</span>
			</a></li>
			<li class="nav-item mb-3"><a href="/logout" class="nav-link"><i
					class="link-icon mdi mdi-logout"></i> <span class="link-title">Logout</span>
			</a></li>


		</ul>
	</div>
</nav>