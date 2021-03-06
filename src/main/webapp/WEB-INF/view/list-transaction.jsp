<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<head>
<title>List Transaction Items</title>

<%@include file="head.jsp"%>

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
									class="form-control" required="required" />
							</div>
							
							<div class="form-group">
								<label>Category</label>
								<select name="categoryId"
									class="form-control" required="required">
									<option value="0" label="Chose category" />
									<c:forEach var="list" items="${categoryList}">
										<option value="${list.categoryId}">${list.categoryName}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label>Amount</label>
								<div class="input-group">
									<form:input type="number" min="0" step="0.01"
										data-number-to-fixed="2" data-number-stepfactor="100"
										class="form-control currency" path="transactionAmount"
										required="required" />
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
						<input type="submit" value="Search" class="add-button" required />
					</form:form>
				</div>

				<div class="container form-group">
					<form:form action="filterByDate" method="POST">
						<div class="input-group">
							Filter by date:
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">From</span>
							</div>
							<input type="date" name="transactionDateFrom" value="${dateFrom}"
								placeholder="Date from" aria-label="DateFrom"
								aria-describedby="basic-addon1" required />
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon2">To</span>
							</div>
							<input type="date" name="transactionDateTo" value="${dateTo}"
								placeholder="Date to" aria-label="DateTo"
								aria-describedby="basic-addon2" required /> <input type="submit"
								value="Filter">
						</div>
					</form:form>
					<c:if test="${transError != null}">
						<div class="alert alert-danger col-xs-offset-1 col-xs-10">
							${transError}</div>
					</c:if>
				</div>
			</div>
			<div class="col-2 align-bottom">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#saveTransaction" accesskey="7">Add
					transaction</button>
			</div>
		</div>


		<c:if test="${addTransactionError != null}">
			<div class="alert alert-danger col-xs-offset-1 col-xs-10">
				You must fill out required filed (Date, Category, Amount)
				<form:errors path="*" />
			</div>
		</c:if>
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
							<td><fmt:formatNumber type="currency" currencySymbol="zł"
									minFractionDigits="2" maxFractionDigits="2" pattern="###.## zł"
									value="${transactionItem.transactionAmount}" /></td>
							<td>${transactionItem.transactionDescription}</td>
							<td><a class="updateLink" href="${updateLink}" title="Edit"><i
									class="fas fa-edit"></i></a> | <a class="updateLink"
								href="${deleteLink}" title="Delete"
								onclick="if(!(confirm('Are you sure you want to delete this item?'))) return false"><i
									class="fas fa-trash-alt"></i></a></td>
						</tr>

					</c:forEach>
					<tr class="table-sum">
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
			var table = document.getElementById("table"), sumVal = 0.00, cellVal = 0.00;

			for (var i = 1; i < table.rows.length; i++) {
				cellVal = table.rows[i].cells[2].innerHTML;
				sumVal = sumVal + parseFloat(cellVal.replace(',', '.'));
			}
			console.log(sumVal);
			document.getElementById("sum").innerHTML = sumVal.toFixed(2)
					.replace('.', ',');
		</script>

	</div>
</body>

</html>