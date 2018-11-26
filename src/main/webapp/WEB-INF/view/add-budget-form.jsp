<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>

<html>

<head>
<title>Create New Budget</title>

<%@include file="head.jsp"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login-page-style.css" />

</head>
<body>

	<div class="container">
		<%@include file="navbar.jsp"%>
		<div class="row justify-content-center">
			<div id="add-budget-card" class="card">

				<div class="card-header">
					<div class="row justify-content-md-center">
						<h2>
							Create new <span class="badge badge-secondary">Budget</span>
						</h2>
					</div>
				</div>

				<form:form action="addNewBudget" method="POST" modelAttribute="newBudget">
					<form:hidden path="budgetId" />
					<%-- <form:hidden path="budgetCreationDate" /> --%>
<%-- 					<form:hidden path="budgetDeatailList" />
					<form:hidden path="budgetUser" /> --%>
					<div class="card-header">
						<label><input type="checkbox" id="selectFromBudgetBox"
							onclick="disableSelect()" />Tick if you want to create based on
							existing budget</label>

						<div class="form-group">
							<select name="oldBudgetId" id="budgetSelect" disabled>
								<option value=0>Select budget</option>
								<c:forEach items="${budgetList}" var="budget">
									<option value="${budget.budgetId}">${budget.budgetName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="card-body">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fas fa-signature"></i></span>
							</div>
							<form:input path="budgetName" placeholder="Name (*)"
								class="form-control" />
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-info"></i></span>
							</div>
							<form:input path="budgetDescription" placeholder="Description"
								class="form-control" />
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fas fa-calendar-alt"></i></span>
							</div>
							<form:input type="date" path="budgetDateFrom"
								placeholder="Date From" class="form-control" />
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fas fa-calendar-alt"></i></span>
							</div>
							<form:input type="date" path="budgetDateTo" placeholder="Date To"
								class="form-control" />
						</div>
					</div>
					<div class="card-footer">
						<div class="form-group">
							<input type="submit" class="btn float-right login_btn"
								value="Save" />
							<%-- <form>
								<button
									onclick="goBack()"
									type="button" class="btn float-left back_btn">Back</button>
							</form> --%>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function disableSelect() {
			if (document.getElementById("selectFromBudgetBox").checked == true) {
				document.getElementById("budgetSelect").disabled = false;
				document.getElementById("budgetSelect1").disabled = false;
			} else {
				document.getElementById("budgetSelect").disabled = true;
				document.getElementById("budgetSelect1").disabled = true;
			}
		}
	</script>


</body>


</html>