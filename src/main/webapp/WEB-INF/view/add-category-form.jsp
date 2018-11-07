<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save Category</title>
<%@include file="head.jsp"%>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<div id="container">
		<h3>Save Category</h3>
		<form:form action="addCategory" modelAttribute="category"
			method="POST">
			<form:hidden path="categoryId" />
			<table>
				<tbody>
					<tr>
						<td><label>Category name</label></td>
						<td><form:input path="categoryName" /></td>
					</tr>
					<tr>
						<td><label>Operation type</label></td>
						<td><form:select path="categoryOperationType">
								<form:option value="0" label="Select operation type" />
								<form:options />
								<%--<form:options itemValue="operationMultiplier" />--%>
							</form:select></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				</tbody>
			</table>


		</form:form>

		<%--<div style="clear; both;"></div>--%>

		<p>
			<a href="${pageContext.request.contextPath}/category/list">Back
				to list</a>
		</p>

	</div>


</body>
</html>