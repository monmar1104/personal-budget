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
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/budget/list">Budgets<span
					class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/category/list">Category</a>
			</li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Configuration </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/category/showFormForAdd">Add
						Category</a> <a class="dropdown-item" href="#">Define Budget
						header</a> <a class="dropdown-item" href="#">Something else here</a>
				</div></li>
		</ul>
	</div>
	<c:if test="${sessionScope.user!=null}">
	<form action="/logout" method="post">
		<input class="btn btn-info btn-sm" type="submit" value="Log out: ${sessionScope.user.userName}"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	</c:if>
</nav>
