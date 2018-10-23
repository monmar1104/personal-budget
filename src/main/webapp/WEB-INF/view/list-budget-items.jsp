<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
    <title>List Budget Item</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
            integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
            integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
            crossorigin="anonymous"></script>

    <script data-require="jquery@2.0.3" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</head>
<body>
<%@include file="navbar.jsp"%>

<div id="wrapper">
    <div id="header">
        <h2>BUDGET DETAILS FOR ${budgetDetailList.get(0).budget.budgetName}</h2>
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
                <th>Category Name</th>
                <th>Amount</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
            <c:forEach var="budgetItem" items="${budgetDetailList}">
                <c:url var="updateLink" value="#">
                    <c:param name="budgetDetailId" value="${tempCustomer.id}"></c:param>
                </c:url>
                <c:url var="deleteLink" value="#">
                    <c:param name="budgetDetailId" value="${tempCustomer.id}"></c:param>
                </c:url>

                <tr>
                    <td>${budgetItem.category.categoryName} </td>
                    <td>${budgetItem.budgetDetailAmount} </td>
                    <td>${budgetItem.budgetDetailDescription} </td>
                    <td><a href="${updateLink}">Update</a> |
                        <a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a></td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>


</body>

</html>