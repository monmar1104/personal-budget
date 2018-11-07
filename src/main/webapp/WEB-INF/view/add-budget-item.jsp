<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
    <title>List Budget Item</title>
    <%@include file="head.jsp" %>
</head>
<body>


            <div>
                <form:form action="addBudgetItem" modelAttribute="budgetDetail"
							method="POST">
							<form:hidden path="budgetDetailId"/>
							<div class="form-group">
								<label>Category</label>
								<form:select name="category" path="category"
									class="form-control">
									<form:option value="${budgetDetail.category.categoryId}" label="${budgetDetail.category.categoryName}" />
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


</body>
</html>