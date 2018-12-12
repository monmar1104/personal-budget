<html>
<head>
    <!--Load the AJAX API-->
    <%@include file="head.jsp" %>
   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <script type="text/javascript">
   
   
   
   $(document).ready(function(){
	   $.ajax({
		   type : 'GET',
		   headers : {
			   Accept : "application/json; charset=utf-8",
			   "Content-Type" : "application/json; charset=utf-8"
		   },
		   url : '${pageContext.request.contextPath}/stats/getStats',
		   success : function(result) {
			   google.charts.load('current', {'packages':['corechart']});
			   google.charts.setOnLoadCallback(function() {
				   drawChart(result);
				   });
		   }
	   });
	   
	   function drawChart(result) {
		
           var data = new google.visualization.DataTable();
           data.addColumn('string', 'Category');
           data.addColumn('number', 'Amount');
      		var dataArray = [];
      		$.each(result, function(i, obj){
      			dataArray.push([obj.category, obj.amount]);
      		})
      		data.addRows(dataArray);
        
           var options = {'title':'Exspenses in current month',
               'width':800,
               'height':400};
           var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
           chart.draw(data, options);
       }
	   
   });

        
    </script>
</head>

<body>
<!--Div that will hold the pie chart-->
<div id="chart_div"></div>
</body>
</html>