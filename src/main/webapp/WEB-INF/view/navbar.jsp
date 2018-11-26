<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div id="logo"><h3> <a href="${pageContext.request.contextPath}/">Where-Is-My-Money</a> </h3></div>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/transaction/list">Transactions
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/budget/list">Budgets<span
					class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/service/break">Statistics</a>
			</li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Configuration </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/category/list">Categories</a>  
					<a class="dropdown-item" href="${pageContext.request.contextPath}/service/break">Settings</a>
				</div>
			</li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="adminNavbarDropdown" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> <i class="fas fa-user"></i> </a>
				<div class="dropdown-menu  justify-content-end" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/category/list">User profile</a> 
					<a class="dropdown-item" href="${pageContext.request.contextPath}/service/break">Admin panel</a> 
					<a class="dropdown-item" href="${pageContext.request.contextPath}/service/break">
						<c:if test="${sessionScope.user!=null}">
							<form action="/logout" method="post">
								<input class="btn btn-info btn-sm" type="submit" value="Log out: ${sessionScope.user.userName}"/>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form>
						</c:if>
						<c:if test="${sessionScope.user==null}">
							<form action="${pageContext.request.contextPath}/showMyLoginPage">
								<input class="btn btn-info btn-sm" type="submit" value="Log in"/>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form>
						</c:if>
					</a>
				</div>
			</li>
		</ul>
	</div>
</nav>
