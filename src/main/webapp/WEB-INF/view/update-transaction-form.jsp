<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Update transaction</title>
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

	<div class="container">
		<%@include file="navbar.jsp"%>
		<div class="row justify-content-center">
			<div id="add-budget-card" class="card">

				<div class="card-header">
					<div class="row justify-content-md-center">
						<h2>
							Update <span class="badge badge-secondary">Transaction</span>
						</h2>
					</div>
				</div>

				<form:form action="addTransaction" modelAttribute="transaction"
					method="POST">
					<div class="card-body">

						<form:hidden path="transactionId" />

						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fas fa-calendar-alt"></i></span>
							</div>
							<form:input path="transactionDate" type="date"
								class="form-control" />
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fas fa-dollar-sign"></i></span>
							</div>
							<form:input type="number" path="transactionAmount"
								placeholder="Amount[zł]" min="0" step="0.01"
								data-number-to-fixed="2" data-number-stepfactor="100"
								class="form-control currency" />
						</div>

						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-info"></i></span>
							</div>
							<form:input path="transactionDescription" class="form-control" />
						</div>

						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fab fa-contao"></i></span>
							</div>
							<form:select name="category" path="category" class="form-control">
								<option value="${transaction.category.categoryId}">${transaction.category.categoryName}</option>
								<c:forEach var="list" items="${categoryList}">
									<option value="${list.categoryId}">${list.categoryName}</option>
								</c:forEach>
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