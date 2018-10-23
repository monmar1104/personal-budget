<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <h2>TRANSACTION LIST</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#saveTransaction" accesskey="7">Add
            transaction
        </button>
        <%--<button type="button" onclick="window.location.href='showAddTransactionForm'; return false;" class="btn btn-primary">Add transaction</button>--%>

        <!-- Modal -->
        <div class="modal fade" id="saveTransaction" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Add transaction</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form:form action="addTransaction" modelAttribute="transaction" method="POST">

                                <div class="form-group">
                                    <label>Transaction date</label>
                                    <form:input type="date" path="transactionDate" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <form:select name="category" path="category" class="form-control">
                                        <form:option value="0" label="Chose category"/>
                                        <c:forEach var="list" items="${categoryList}">
                                            <option value="${list.categoryId}">${list.categoryName}</option>
                                        </c:forEach>
                                    </form:select>
                                </div>

                                <div class="form-group">
                                    <label>Amount</label>
                                    <div class="input-group">
                                        <form:input type="number" min="0" step="0.01" data-number-to-fixed="2"
                                                    data-number-stepfactor="100" class="form-control currency"
                                                    path="transactionAmount" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <form:input type="textarea" path="transactionDescription" class="form-control"/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>

        <%--end modal --%>

        <p/>
        <p/>
        <div>
            <form:form action="search" method="POST">
                Search transaction: <input type="text" name="transactionName"/>

                <input type="submit" value="Search" class="add-button"/>
            </form:form>
        </div>


        <table class="grid">
            <thead>
            <tr>
                <td index=0>Date<div class="filter"></div></td>
                <td index=1>Category Name<div class="filter"></div></td>
                <td index=2>Amount<div class="filter"></div></td>
                <td index=3>Description<div class="filter"></div></td>
                <td>Action</td>
            </tr>
            </thead>
            <tbody>
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
                        <a href="${deleteLink}"
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