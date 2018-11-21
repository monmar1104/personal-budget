<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<head>
<title>List Categories</title>

<%@include file="head.jsp" %>
</head>
<body>


	<div class="container">

		<%@include file="navbar.jsp"%>

		<div class="row justify-content-md-center">
			<h2>
				Category <span class="badge badge-secondary">List</span>
			</h2>
		</div>


		<!-- Modal -->
		<div class="modal fade" id="saveCategory" tabindex="-1"
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
						<form:form action="addCategory" modelAttribute="category"
							method="POST">

							<div class="form-group">
								<label>Category name</label>
								<form:input type="text" path="categoryName"
									class="form-control" autofocus="autofocus" />
							</div>
							<div class="form-group">
								<label>Transaction type</label>
								<form:select path="categoryOperationType"
									class="form-control">
									<form:option value="0" label="Select transaction type" />
									<form:options/>
								</form:select>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Save</button>
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
			</div>
			<div class="col-2 align-bottom">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#saveCategory" accesskey="7">Add
					category</button>
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
						<th scope="col" index=1>Transaction Type
							<div class="filter"></div>
						</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="category" items="${categoryList}">
						<c:url var="updateLink"
							value="/transaction/showTransactionFormUpdate">
							<c:param name="categoryId"
								value="${category.categoryId}"></c:param>
						</c:url>
						<c:url var="deleteLink" value="/transaction/delete">
							<c:param name="categoryId"
								value="${category.categoryId}"></c:param>
						</c:url>

						<tr>
							<td>${category.categoryName}</td>
							<td>${category.categoryOperationType}</td>
							<td><a class="updateLink" href="${updateLink}">Update</a> | 
							<a class="updateLink" href="${deleteLink}"
								onclick="if(!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</body>

</html>