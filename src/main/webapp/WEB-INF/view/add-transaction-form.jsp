<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Save Customer</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>
<body>

<div id="container">
    <h3>Save Category</h3>
    <form:form action="addTransaction" modelAttribute="transaction" method="POST">
        <form:hidden path="transactionId"/>
        <table>
            <tbody>
            <tr>
                <td><label>Date</label></td>
                <td><form:input path="transactionDate" class="form-control"/></td>
            </tr>
            <tr>
                <td><label>Amount</label></td>
                <td><form:input path="transactionAmount" class="form-control"/></td>
            </tr>

            <tr>
                <td><label>Description</label></td>
                <td><form:input path="transactionDescription" class="form-control"/></td>
            </tr>
            <%--<tr>--%>
                <%--<td><label>Category</label></td>--%>
                <%--<td><form:input path="category.categoryName"/></td>--%>
            <%--</tr>--%>
            <tr>
                <td><label>Category</label></td>
                <td>
                    <form:select name="category" path="category" class="form-control">
                        <option value="${transaction.category.categoryId}">${transaction.category.categoryName}</option>
                        <c:forEach var="list" items="${categoryList}">
                            <option value="${list.categoryId}">${list.categoryName}</option>
                        </c:forEach>
                    </form:select>
                        <%--${list.categoryName}--%>
                </td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>

            </tbody>
        </table>


    </form:form>

    <%--<div style="clear; both;"></div>--%>

    <p>
        <a href="${pageContext.request.contextPath}/category/list">Back to list</a>
    </p>

</div>


</body>
</html>