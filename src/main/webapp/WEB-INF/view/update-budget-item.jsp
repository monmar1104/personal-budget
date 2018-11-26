<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
    <title>Update Budget Item</title>
    <%@include file="head.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/login-page-style.css">
</head>
<body>


<div class="container">
		<%@include file="navbar.jsp"%>
		<div class="row justify-content-center">
			<div id="add-budget-card" class="card">

				<div class="card-header">
					<div class="row justify-content-md-center">
						<h2>
							Update <span class="badge badge-secondary">Budget Item</span>
						</h2>
					</div>
				</div>

				<form:form action="addBudgetItem" modelAttribute="budgetDetail"
							method="POST">
					<div class="card-body">

						<form:hidden path="budgetDetailId" />
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fab fa-contao"></i></span>
							</div>
							<form:select name="category" path="category"
									class="form-control">
									<form:option value="${budgetDetail.category.categoryId}" label="${budgetDetail.category.categoryName}" />
									<c:forEach var="category" items="${categoryList}">
										<form:option value="${category.categoryId}">${category.categoryName}</form:option>
									</c:forEach>
								</form:select>
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fas fa-dollar-sign"></i></span>
							</div>
							<form:input type="number" path="budgetDetailAmount"
								placeholder="Amount[zł]" min="0" step="0.01"
								data-number-to-fixed="2" data-number-stepfactor="100"
								class="form-control currency" />
						</div>
						
						
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-info"></i></span>
							</div>
							<form:input path="budgetDetailDescription" class="form-control" />
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