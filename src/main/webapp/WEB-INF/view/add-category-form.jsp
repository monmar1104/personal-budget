<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Save Customer</title>

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

<div id="container">
    <h3>Save Category</h3>
    <form:form action="addCategory" modelAttribute="category" method="POST">
        <form:hidden path="categoryId"/>
        <table>
            <tbody>
            <tr>
                <td><label>Category name</label></td>
                <td><form:input path="categoryName"/></td>
            </tr>
            <tr>
                <td><label>Operation type</label></td>
                <td>
                    <form:select path="categoryOperationType">
                        <form:option value="0" label="Chose operation type"/>
                        <form:options />
                        <%--<form:options itemValue="operationMultiplier" />--%>
                    </form:select>

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