<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
    <title>List Budget Item</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

</head>
<body>


            <div>
                <form:form action="addTransaction" modelAttribute="transaction" method="POST">
                    <table>
                        <tbody>
                        <tr>
                            <td><label>Transaction date</label></td>
                            <td><form:input type="date" path="transactionDate"/></td>
                        </tr>
                        <tr>
                            <td><label>Category</label></td>
                            <td>
                                <form:select name="category" path="category">
                                    <form:option value="0" label="Chose category"/>
                                    <c:forEach var="list" items="${categoryList}">
                                        <option value="${list.categoryId}">${list.categoryName}</option>
                                    </c:forEach>
                                </form:select>

                            </td>
                        </tr>
                        <tr>
                            <div class="form-row">
                                <td><label>Amount</label></td>
                                <div class="input-group">
                                    <td><form:input type="number" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" path="transactionAmount"/></td>
                                </div>
                            </div>
                        </tr>
                        <tr>
                            <td><label>Description</label></td>
                            <td><form:input type="text" path="transactionDescription"/></td>
                        </tr>

                        </tbody>
                    </table>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>

                </form:form>
            </div>


</body>
</html>