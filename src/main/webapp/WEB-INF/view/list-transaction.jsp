<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
    <title>List Budget Item</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>TRANSACTION LIST</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <input type="button" value="Add Budget Item"
               onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button" />

        <div>
            <form:form action="search" method="POST">
                Search category: <input type="text" name="categoryName" />

                <input type="submit" value="Search" class="add-button" />
            </form:form>
        </div>


        <table>
            <tr>
                <th>Date</th>
                <th>Category Name</th>
                <th>Amount</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
            <c:forEach var="transactionItem" items="${transactionList}">
                <c:url var="updateLink" value="#">
                    <c:param name="budgetDetailId" value="${transactionItem.transactionId}"></c:param>
                </c:url>
                <c:url var="deleteLink" value="#">
                    <c:param name="budgetDetailId" value="${transactionItem.transactionId}"></c:param>
                </c:url>

                <tr>
                    <td>${transactionItem.transactionDate} </td>
                    <td>${transactionItem.category.categoryName} </td>
                    <td>${transactionItem.transactionAmount} </td>
                    <td>${transactionItem.transactionDescription} </td>
                    <td><a href="${updateLink}">Update</a> |
                        <a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a></td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>


</body>

</html>