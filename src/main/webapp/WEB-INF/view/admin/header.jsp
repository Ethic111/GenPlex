<nav class="navbar">
	<a href="#" class="sidebar-toggler"> <i data-feather="menu"></i>
	</a>
	<div class="navbar-content">

		<ul class="navbar-nav">

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="profileDropdown"
				role="button" data-bs-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <img class="wd-30 ht-30 rounded-circle"
					src="<%=request.getContextPath()%>/adminresources/images/face1.jpg" alt="profile" />
			</a>
				<div class="dropdown-menu p-0" aria-labelledby="profileDropdown">
					<ul class="list-unstyled p-1">
						<li class="dropdown-item py-2"><a href="/logout"
							class="text-body ms-0"> <i
								class="me-1 icon-md mdi mdi-logout"></i> <span>Log
									Out</span>
						</a></li>
					</ul>
				</div></li>
		</ul>
	</div>
</nav>