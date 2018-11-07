<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>

<html>
<body>

	<div class="container">

		<%@include file="navbar.jsp"%>

		<div class="row justify-content-md-center">
			<h2>
				Budget <span class="badge badge-secondary">Items</span>
			</h2>
		</div>


		<!-- Modal -->
		<div class="modal fade" id="saveBudgetItem" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Add budget
							item</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form:form action="addBudgetItem" modelAttribute="budgetDetail"
							method="POST">

							<div class="form-group">
								<label>Category</label>
								<form:select name="category" path="category"
									class="form-control">
									<form:option value="0" label="Select category" />
									<c:forEach var="category" items="${categoryList}">
										<form:option value="${category.categoryId}">${category.categoryName}</form:option>
									</c:forEach>
								</form:select>
							</div>

							<div class="form-group">
								<label>Amount</label>
								<div class="input-group">
									<form:input type="number" min="0" step="0.01"
										data-number-to-fixed="2" data-number-stepfactor="100"
										class="form-control currency" path="budgetDetailAmount" />
								</div>
							</div>
							<div class="form-group">
								<label>Description</label>
								<form:input type="textarea" path="budgetDetailDescription"
									class="form-control" />
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Save
									changes</button>
							</div>
						</form:form>
					</div>

				</div>
			</div>
		</div>


		<%--end modal --%>

		<p />
		<p />
		<div class="container row justify-content-center">
			<div class="col-5 border-radius">
				<form:form action="listBudgetItemsById" method="post">
					<div class="form-group">

						<select name="budgetId">
							<option value="${currentBudget.budgetId}">${currentBudget.budgetName}</option>
							<c:forEach items="${budgetList}" var="budget">
								<option value="${budget.budgetId}">${budget.budgetName}</option>
							</c:forEach>
						</select>
						<button type="submit" class="btn btn-primary">Select</button>
					</div>
				</form:form>
			</div>
		</div>
		<div class="container justify-content-center border-radius row">
			<div class="col-6">
				<div class="container form-group">
					<form:form action="search" method="POST">
                    Search category: <input type="text"
							name="categoryName" />
						<input type="submit" value="Search" class="btn btn-primary" />
					</form:form>
				</div>
			</div>


			<div class="col-2 align-bottom justify-content-end">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#saveBudgetItem" accesskey="7">Add budget
					item</button>
			</div>
		</div>

		<p />
		<p />
		<div class="table-responsive">
			<table id="table" class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col" index=0>Category Name
							<div class="filter"></div>
						</th>
						<th scope="col" index=1>Amount
							<div class="filter"></div>
						</th>
						<th scope="col" index=2>Spent Amount
							<div class="filter"></div>
						</th>
						<th scope="col" index=3>% of Usage
							<div class="filter"></div>
						</th>
						<th scope="col" index=4>Description
							<div class="filter"></div>
						</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="budgetItem" items="${budgetDetailList}">
						<c:url var="updateLink"
							value="/budget/showAddBudgetItemFormUpdate">
							<c:param name="budgetDetailId"
								value="${budgetItem.budgetDetailId}"></c:param>
						</c:url>
						<c:url var="deleteLink" value="/budget/delete">
							<c:param name="budgetDetailId"
								value="${budgetItem.budgetDetailId}"></c:param>
						</c:url>
						<tr>

							<td>${budgetItem.category.categoryName}</td>

							<td><fmt:formatNumber type = "currency" minFractionDigits = "2" maxFractionDigits = "2" value = "${budgetItem.budgetDetailAmount}" /></td>
							<c:choose>
								<c:when
									test="${sumCategoryMap.get(budgetItem.category.categoryId)!=null}">
									<td><fmt:formatNumber type = "currency" minFractionDigits = "2" maxFractionDigits = "2" value = "${sumCategoryMap.get(budgetItem.category.categoryId)}"/></td>
								</c:when>
								<c:otherwise>
									<td><fmt:formatNumber type = "currency" minFractionDigits = "2" maxFractionDigits = "2" value = "0"/></td>
								</c:otherwise>
							</c:choose>
							
							<td> <fmt:formatNumber type = "percent" 
													minFractionDigits = "1" 
													maxFractionDigits = "1" 
													value = "${sumCategoryMap.get(budgetItem.category.categoryId) / budgetItem.budgetDetailAmount}"/></td>
							<td>${budgetItem.budgetDetailDescription}</td>
							<td><a href="${updateLink}">Update</a> | <a
								href="${deleteLink}"
								onclick="if(!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a>
							</td>
						</tr>

					</c:forEach>
					<tr class="table-info">
						<td>Sum</td>
						<td id="sum">0</td>
						<td id="sum1">0</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>

		<script type="text/javascript">
			var table = document.getElementById("table"), plan = 0.00;
			var table1 = document.getElementById("table"), expend = 0.00;

			for (var i = 1; i < table.rows.length; i++) {
				plan = plan + parseFloat(table.rows[i].cells[1].innerHTML.replace(',', '.'));
				expend = expend + parseFloat(table1.rows[i].cells[2].innerHTML.replace(',', '.'));
			}
			console.log(plan);
			console.log(expend);
			document.getElementById("sum").innerHTML = plan.toFixed(2).replace('.', ',');
			document.getElementById("sum1").innerHTML = expend.toFixed(2).replace('.', ',');
		</script>
	</div>
</body>
<head>
<title>List Budget Items</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
	integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
	crossorigin="anonymous"></script>

<script data-require="jquery@2.0.3" data-semver="2.0.3"
	src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>

</head>

</html>