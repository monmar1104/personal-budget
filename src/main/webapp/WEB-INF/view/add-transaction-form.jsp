<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
    <form action="addTransactions" modelAttribute="transactions" method="POST">

        <table>
            <tbody>
            <tr>
                <td><label>Date</label></td>
                <td><input path="transactionDate"/></td>
            </tr>
            <tr>
                <td><label>Amount</label></td>
                <td><input path="transactionAmount"/></td>
            </tr>

            <tr>
                <td><label>Description</label></td>
                <td><input path="transactionDescription"/></td>
            </tr>
            <tr>
                <td><label>Category</label></td>
                <td><input path="${transactions.category.categoryId}"/></td>
            </tr>
            <%--<tr>--%>
                <%--<td><label>Operation type</label></td>--%>
                <%--<td>--%>
                    <%--<form:select path="category">--%>
                        <%--<form:option value="0" label="Chose operation type"/>--%>
                        <%--<form:options />--%>
                        <%--&lt;%&ndash;<form:options itemValue="operationMultiplier" />&ndash;%&gt;--%>
                    <%--</form:select>--%>

                <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>

            </tbody>
        </table>


    </form>

    <%--<div style="clear; both;"></div>--%>

    <p>
        <a href="${pageContext.request.contextPath}/category/list">Back to list</a>
    </p>

</div>


</body>
</html>