<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Personal Budget</title>

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

<%@include file="navbar.jsp" %>


<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/save.jpeg" alt="First slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/10-money-lessons.jpg" alt="Second slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/money_problem.png" alt="Third slide">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>




<%--<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">--%>
    <%--<div class="carousel-inner">--%>
        <%--<div class="carousel-item active">--%>
            <%--<img class="d-block w-100" src="${pageContext.request.contextPath}/resources/homeicon.jpeg" alt="First slide">--%>
        <%--</div>--%>
        <%--<div class="carousel-item">--%>
            <%--<img class="d-block w-100" src="${pageContext.request.contextPath}/resources/10-money-lessons.jpg" alt="Second slide">--%>
        <%--</div>--%>
        <%--<div class="carousel-item">--%>
            <%--<img class="d-block w-100" src="${pageContext.request.contextPath}/resources/money_problem.png" alt="Third slide">--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">--%>
        <%--<span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
        <%--<span class="sr-only">Previous</span>--%>
    <%--</a>--%>
    <%--<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">--%>
        <%--<span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
        <%--<span class="sr-only">Next</span>--%>
    <%--</a>--%>
<%--</div>--%>








</body>
</html>
