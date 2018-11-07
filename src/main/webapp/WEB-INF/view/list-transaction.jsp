<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<head>
<title>List Transaction Items</title>

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
<body>


	<div class="container">

		<%@include file="navbar.jsp"%>

		<div class="row justify-content-md-center">
			<h2>
				Transaction <span class="badge badge-secondary">List</span>
			</h2>
		</div>


		<!-- Modal -->
		<div class="modal fade" id="saveTransaction" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Add
							transaction</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form:form action="addTransaction" modelAttribute="transaction"
							method="POST">

							<div class="form-group">
								<label>Transaction date</label>
								<form:input type="date" path="transactionDate"
									class="form-control" />
							</div>
							<div class="form-group">
								<label>Category</label>
								<form:select name="category" path="category"
									class="form-control">
									<form:option value="0" label="Chose category" />
									<c:forEach var="list" items="${categoryList}">
										<option value="${list.categoryId}">${list.categoryName}</option>
									</c:forEach>
								</form:select>
							</div>

							<div class="form-group">
								<label>Amount</label>
								<div class="input-group">
									<form:input type="number" min="0" step="0.01"
										data-number-to-fixed="2" data-number-stepfactor="100"
										class="form-control currency" path="transactionAmount" />
								</div>
							</div>
							<div class="form-group">
								<label>Description</label>
								<form:input type="textarea" path="transactionDescription"
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
		<div class="container row">
			<div class="col-10">
				<div class="container form-group">
					<form:form action="search" method="POST">
                    Search transaction: <input type="text"
							value="${categoryName}" name="transactionName" />
						<input type="submit" value="Search" class="add-button" />
					</form:form>
				</div>

				<div class="container from-group">
					<form:form action="filterByDate" method="POST">
						<div class="input-group">
							Filter by date:
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">From</span>
							</div>
							<input type="date" name="transactionDateFrom" value="${dateFrom}"
								placeholder="Date from" aria-label="DateFrom"
								aria-describedby="basic-addon1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon2">To</span>
							</div>
							<input type="date" name="transactionDateTo" value="${dateTo}"
								placeholder="Date to" aria-label="DateTo"
								aria-describedby="basic-addon2"> <input type="submit"
								value="Filter">
						</div>
					</form:form>
				</div>
			</div>
			<div class="col-2 align-bottom">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#saveTransaction" accesskey="7">Add
					transaction</button>
			</div>
		</div>


		<p />
		<p />
		<div class="table-responsive">
			<table id="table" class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col" index=0>Date
							<div class="filter"></div>
						</th>
						<th scope="col" index=1>Category Name
							<div class="filter"></div>
						</th>
						<th scope="col" index=2>Amount
							<div class="filter"></div>
						</th>
						<th scope="col" index=3>Description
							<div class="filter"></div>
						</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="transactionItem" items="${transactionList}">
						<c:url var="updateLink"
							value="/transaction/showTransactionFormUpdate">
							<c:param name="transactionId"
								value="${transactionItem.transactionId}"></c:param>
						</c:url>
						<c:url var="deleteLink" value="/transaction/delete">
							<c:param name="transactionId"
								value="${transactionItem.transactionId}"></c:param>
						</c:url>

						<tr>
							<td>${transactionItem.transactionDate}</td>
							<td>${transactionItem.category.categoryName}</td>
							<td><fmt:formatNumber type="currency" minFractionDigits="2"
									maxFractionDigits="2"
									value="${transactionItem.transactionAmount}" /></td>
							<td>${transactionItem.transactionDescription}</td>
							<td><a href="${updateLink}">Update</a> | <a
								href="${deleteLink}"
								onclick="if(!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a>
							</td>
						</tr>

					</c:forEach>
					<tr class="table-info">
						<td>Sum</td>
						<td></td>
						<td id="sum">0</td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>


		<script type="text/javascript">
			var table = document.getElementById("table"), sumVal = 0;

			for (var i = 1; i < table.rows.length; i++) {

				if (isNaN(parseFloat(table.rows[i].cells[2].innerHTML))) {
					alert(table.rows[i].cells[2].innerHTML + "is not a number")
				}
				sumVal = sumVal + parseFloat(table.rows[i].cells[2].innerHTML);
			}
			console.log(sumVal);
			document.getElementById("sum").innerHTML = sumVal.toFixed(2);
		</script>

	</div>
</body>

</html>