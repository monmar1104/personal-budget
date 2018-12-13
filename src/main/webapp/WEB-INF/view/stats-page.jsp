<html>

<head>
<title>Statistics</title>
<%@include file="head.jsp"%>
</head>

<body>
	<div class="container">
	<%@include file="navbar.jsp"%>
		<div>
			<h2 style="{color: white;}">Stats - main page</h2>

			<hr>
			<a class="updateLink"
				href="${pageContext.request.contextPath}/stats/showSumOfExpensesChart">Show
				chart</a>
		</div>
	</div>
</body>

</html>