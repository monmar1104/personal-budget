<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="chooseBudgetModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div id="login-modal" class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><span>Select Budget</span></h4>
            </div>
            <div class="modal-body">
                <form action="#" method="POST">

                    <div class="form-group">
                        <label>Select Budget</label>
                        <select class="form-control" name="budgetId">
                            <option value="0" selected>Select budget</option>
                            <c:forEach var="list" items="${budgetList}">
                                <option value="${list.budgetId}">${list.budgetName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Select</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
