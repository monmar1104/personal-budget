<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Update Category</title>
<%@include file="head.jsp"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/login-page-style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	
	<!-- new -->
	
	<div class="container">
		<%@include file="navbar.jsp"%>
		<div class="row justify-content-center">
			<div id="add-budget-card" class="card">

				<div class="card-header">
					<div class="row justify-content-md-center">
						<h2>
							Update <span class="badge badge-secondary">Category</span>
						</h2>
					</div>
				</div>

				<form:form action="addCategory" modelAttribute="category" method="POST">
					<div class="card-body">

						<form:hidden path="categoryId" />

						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fab fa-contao"></i></span>
							</div>
							<form:input path="categoryName" class="form-control" />
						</div>

						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-money-check-alt"></i></span>
							</div>
							<form:select path="categoryOperationType"
									class="form-control">
									<%-- <form:option value="${category.categoryOperationType}" label="${category.categoryOperationType}" /> --%>
									<form:options/>
								</form:select>
						</div>


					</div>
					<div class="card-footer">
						<div class="form-group">
							<input type="submit" class="btn float-right login_btn"
								value="Save" />

							<button onclick="goBack()" type="button"
								class="btn float-left back_btn">Back</button>

						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>


</body>
</html>