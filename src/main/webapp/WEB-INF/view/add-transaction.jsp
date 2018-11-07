<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<head>
<title>List Budget Item</title>

<%@include file="head.jsp"%>
</head>
<body>


	<div>
		<form:form action="addTransaction" modelAttribute="transaction"
			method="POST">
			<table>
				<tbody>
					<tr>
						<td><label>Transaction date</label></td>
						<td><form:input type="date" path="transactionDate" /></td>
					</tr>
					<tr>
						<td><label>Category</label></td>
						<td><form:select name="category" path="category">
								<form:option value="0" label="Chose category" />
								<c:forEach var="list" items="${categoryList}">
									<option value="${list.categoryId}">${list.categoryName}</option>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr>
						<div class="form-row">
							<td><label>Amount</label></td>
							<div class="input-group">
								<td><form:input type="number" min="0" step="0.01"
										data-number-to-fixed="2" data-number-stepfactor="100"
										class="form-control currency" path="transactionAmount" /></td>
							</div>
						</div>
					</tr>
					<tr>
						<td><label>Description</label></td>
						<td><form:input type="text" path="transactionDescription" /></td>
					</tr>

				</tbody>
			</table>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">Save changes</button>
			</div>

		</form:form>
	</div>


</body>
</html>