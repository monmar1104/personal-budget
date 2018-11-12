<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>

<html>

<head>
<title>Create New Budget</title>
<%@include file="head.jsp"%>

</head>
<body>

	<div class="container">

		<%@include file="navbar.jsp"%>

		<div class="row justify-content-md-center">
			<h2>
				Create new <span class="badge badge-secondary">Budget</span>
			</h2>
		</div>

		<div class="row justify-content-center">
			<div class="col">
				<label><input type="checkbox" id="selectFromBudgetBox"
					onclick="disableSelect()" />Tick if you want to create based on
					existing budget</label>
				<form:form action="addNewBudget" method="post"
					modelAttribute="newBudget">
					<div class="form-group">
						<select name="budgetId" id="budgetSelect" disabled>
							<option value="0">Select budget</option>
							<c:forEach items="${budgetList}" var="budget">
								<option value="${budget.budgetId}">${budget.budgetName}</option>
							</c:forEach>
						</select>
						<button type="submit" class="btn btn-primary" id="budgetSelect1"
							disabled>Select</button>
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<form:input path="budgetName" placeholder="Name (*)"
							class="form-control" />
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<form:input path="budgetDescription" placeholder="Description"
							class="form-control" />
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-date"><i class="fas fa-user"></i></span>
						</div>
						<form:input path="budgetDateFrom" placeholder="Date From"
							class="form-control" />
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-date"><i class="fas fa-user"></i></span>
						</div>
						<form:input path="budgetDateTo" placeholder="Date To"
							class="form-control" />
					</div>
					<div class="form-group">
						<input type="submit" class="btn float-right login_btn" />
					</div>

				</form:form>
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

	</div>
</body>


</html>