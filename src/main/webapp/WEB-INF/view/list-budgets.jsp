<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>

<html>

<head>
<title>List Budget Items</title>
<%@include file="head.jsp"%>

</head>
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


		<%--end of modal --%>

		<p />
		<p />
		<div class="row justify-content-center">
			<div class="col">
				<form:form action="listBudgetItemsById" method="post">
					<div class="form-group">
						<select name="budgetId">
							<c:if test="${currentBudget.budgetId!=0}">
								<option value="${currentBudget.budgetId}">${currentBudget.budgetName}</option>
							</c:if>
							<c:if test="${currentBudget.budgetId==0}">
								<option style="color: red;" value="0">No Budgets</option>
							</c:if>
							<c:forEach items="${budgetList}" var="budget">
								<option value="${budget.budgetId}">${budget.budgetName}</option>
							</c:forEach>
						</select>
						<button type="submit" class="btn btn-primary">Select</button>
					</div>
				</form:form>
			</div>
			<div>
				<form:form action="showAddBudgetForm" method="get">
					<button class=" col btn btn-primary" type="submit">Add
						Budget</button>
				</form:form>
			</div>
		</div>
		<div class="justify-content-center row">
			<div class="col-6">
				<div class="container form-group">
					<form:form action="search?budgetId=${currentBudget.budgetId}"
						method="POST">
					
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
			<c:if test="${budgetError != null}">
				<div class="alert alert-danger col-xs-offset-1 col-xs-10">
					${budgetError}</div>
			</c:if>
		</div>

		<p />
		<p />
		<div class="table-responsive">
			<table id="table" class="table table-hover table-fixed">
				<thead class="thead-dark">
					<tr>
						<th class="col-xs-3" scope="col" index=0>Category Name
							<div class="filter"></div>
						</th>
						<th class="col-xs-1" scope="col" index=1>Amount
							<div class="filter"></div>
						</th>
						<th class="col-xs-1" scope="col" index=2>Spent Amount
							<div class="filter"></div>
						</th>
						<th class="col-xs-1" scope="col" index=3>% of Usage
							<div class="filter"></div>
						</th>
						<th class="col-xs-4" scope="col" index=4>Description
							<div class="filter"></div>
						</th>
						<th class="col-xs-2" scope="col">Action</th>
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
						<c:url var="expenseLink"
							value="/transaction/searchCategory">
							<c:param name="transactionName"
								value="${budgetItem.category.categoryName}"></c:param>
								<c:param name="budgetId"
								value="${budgetItem.budget.budgetId}"></c:param>
						</c:url>
						<tr>
							<td class="col-xs-3"><a class="catName" href="${expenseLink}">${budgetItem.category.categoryName}</a></td>

							<td id="budgetAmount" class="col-xs-1"><fmt:formatNumber
									type="number" minFractionDigits="2" pattern="###.## zł"
									maxFractionDigits="2" groupingUsed="false"
									value="${budgetItem.budgetDetailAmount}" /></td>
							<c:choose>
								<c:when
									test="${sumCategoryMap.get(budgetItem.category.categoryId)!=null}">
									<td id="expenseAmount" class="col-xs-1">
									 <fmt:formatNumber
												type="number" pattern="###.## zł" groupingUsed="false"
												minFractionDigits="2" maxFractionDigits="2"
												value="${sumCategoryMap.get(budgetItem.category.categoryId)}" />
									</td>
								</c:when>
								<c:otherwise>
									<td id="expenseAmount" class="col-xs-1"><fmt:formatNumber
											type="number" pattern="###.## zł" minFractionDigits="2"
											maxFractionDigits="2" value="0" /></td>
								</c:otherwise>
							</c:choose>

							<td id="percent" class="col-xs-1"></td>

							<td class="col-xs-4">${budgetItem.budgetDetailDescription}</td>
							<td class="col-xs-2"><a class="updateLink"
								href="${updateLink}" title="Edit"><i class="fas fa-edit"></i></a>
								| <a class="updateLink" href="${deleteLink}" title="Delete"
								onclick="if(!(confirm('Are you sure you want to delete this item?'))) return false">
									<i class="fas fa-trash-alt"></i>
							</a></td>


						</tr>

					</c:forEach>
					<tr class="table-sum">
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

		<!-- calculating sum of amounts -->
		<script type="text/javascript">
			var table = document.getElementById("table"), plan = 0.00;
			var table1 = document.getElementById("table"), expend = 0.00;
			var currentBudgetAmount = 0.00;
			var currentExpenseAmount = 0.00;

			for (var i = 1; i < table.rows.length; i++) {
				currentBudgetAmount = parseFloat(table.rows[i].cells[1].innerHTML
						.replace(/\s/g, '').replace(',', '.'));

				currentExpenseAmount = parseFloat(table1.rows[i].cells[2].innerHTML
						.replace(/\s/g, '').replace(',', '.'));

				plan = plan + currentBudgetAmount;
				expend = expend + currentExpenseAmount;

			}
			console.log(plan);
			console.log(expend);
			document.getElementById("sum").innerHTML = plan.toFixed(2).replace(
					'.', ',');
			document.getElementById("sum1").innerHTML = expend.toFixed(2)
					.replace('.', ',');
		</script>

		<!-- percent calculating and alert -->
		<script type="text/javascript">
			var currentBudgetAmount = 0.00;
			var currentExpenseAmount = 0.00;
			var percent = 0.00;

			for (var i = 1; i < table.rows.length; i++) {
				currentBudgetAmount = parseFloat(table.rows[i].cells[1].innerHTML
						.replace(/\s/g, '').replace(',', '.'));
					
				currentExpenseAmount = parseFloat(table1.rows[i].cells[2].innerHTML
						.replace(/\s/g, '').replace(',', '.'));
						
				console.log("from loop: "+currentExpenseAmount.toFixed(1).replace('.', ','));

				percent = currentExpenseAmount / currentBudgetAmount * 100;
				if (percent > 100) {
					table.rows[i].cells[3].className = "overrun";
				} else if (percent == 100) {
					table.rows[i].cells[3].className = "plan";
				}

				table.rows[i].cells[3].innerHTML = percent.toFixed(1).replace(
						'.', ',')
						+ " %";

				console.log(currentBudgetAmount.toFixed(1).replace('.', ','));
				console.log(currentExpenseAmount.toFixed(1).replace('.', ','));
				console.log(percent.toFixed(1).replace('.', ','));

			}
		</script>
	</div>
</body>


</html>