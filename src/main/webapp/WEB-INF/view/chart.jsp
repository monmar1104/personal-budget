<html>
<head>

<title>Expenses stats</title>

<%@include file="head.jsp"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
	
	
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$
								.ajax({
									type : 'GET',
									headers : {
										Accept : "application/json; charset=utf-8",
										"Content-Type" : "application/json; charset=utf-8"
									},
									url : '${pageContext.request.contextPath}/stats/getExpensesFromCurrentBudget',
									success : function(result) {
										google.charts.load('current', {
											'packages' : [ 'corechart' ]
										});
										google.charts
												.setOnLoadCallback(function() {
													drawChart(result);
												});
									}
								});

						function drawChart(result) {

							var data = new google.visualization.DataTable();
							data.addColumn('string', 'Category');
							data.addColumn('number', 'Amount');
							var dataArray = [];
							$.each(result, function(j, obj) {
								dataArray.push([ obj.category, obj.amount ]);
							})
							data.addRows(dataArray);

							var options = {
								'title' : 'Exspenses in current month',
								'width' : 1120,
								'height' : 600
							};
							var chart = new google.visualization.PieChart(
									document
											.getElementById('chartCurrentBudget_div'));
							chart.draw(data, options);
						}

					});

</script>
	
<script type="text/javascript">


	$(document)
			.ready(
					function() {
							$.ajax({
									type : 'GET',
									headers : {
										Accept : "application/json; charset=utf-8",
										"Content-Type" : "application/json; charset=utf-8"
									},
									/* url : '${pageContext.request.contextPath}/stats/chartPanelPost?categoryId=69&transactionDateFrom=2018-11-01&transactionDateTo=2018-12-31', */
									url : '${pageContext.request.contextPath}/stats/chartPanelPost?categoryId=${categoryId}&transactionDateFrom=${transactionDateFrom}&transactionDateTo=${transactionDateTo}',
									/* data: 'categoryId='+catId +'&transactionDateFrom='+dateFrom +'&transactionDateTo='+dateTo, */
									success : function(result) {
										google.charts.load('current', {
											'packages' : [ 'corechart' ]
										});
										google.charts
												.setOnLoadCallback(function() {
													drawChart(result);
												});
									}
								});

						function drawChart(result) {

							var data = new google.visualization.DataTable();
							data.addColumn('string', 'Month');
							data.addColumn('number', 'Amount');
							var dataArray = [];
							$.each(result, function(i, obj) {
								dataArray.push([ obj.month, obj.amount ]);
							})
							data.addRows(dataArray);

							var options = {
								'title' : 'Exspenses from selected category',
								'width' : 1120,
								'height' : 600
							};
							var chart = new google.visualization.ColumnChart(
									document
											.getElementById('chartExpensesByDate_div'));
							chart.draw(data, options);
						}

					});
</script>
</head>

<body>
	<div class="container">
		<%@include file="navbar.jsp"%>
		<div class="row justify-content-center bg-dark">
			<div id="chartCurrentBudget_div"></div>
		</div>



		<form action="chartPanelPost" method="GET">
			<div class="form-row justify-content-center">
				<div class="col-sm-5">
					<label>Category</label> <select id="catId" name="categoryId"
						class="form-control">
						<c:forEach var="list" items="${categoryList}">
							<option value="${list.categoryId}">${list.categoryName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-7">
					Filter by date:
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">From</span>
					</div>
					<input id="dateFrom" type="date" name="transactionDateFrom" value="${transactionDateFrom}"
						placeholder="Date from" aria-label="DateFrom"
						aria-describedby="basic-addon1" required />
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon2">To</span>
					</div>
					<input id="dateTo" type="date" name="transactionDateTo" value="${transactionDateTo}"
						placeholder="Date to" aria-label="DateTo"
						aria-describedby="basic-addon2" required /> <input type="submit"
						value="Filter">
				</div>
			</div>
		</form>
		<c:if test="${transError != null}">
			<div class="alert alert-danger col-xs-offset-1 col-xs-10">
				${transError}</div>
		</c:if>

		<div id="chartExpensesByDate_div"></div>

		<hr>
		<div class="row">
			<a class="updateLink"
				href="${pageContext.request.contextPath}/stats/">Back to Stats
				Page</a>
		</div>
	</div>
</body>
</html>