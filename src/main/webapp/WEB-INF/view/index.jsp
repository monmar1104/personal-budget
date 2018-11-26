<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>WhereIsMyMoney</title>
    <%@include file="head.jsp" %>
    
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/login-page-style.css">
	
</head>
</head>
<body>

<div class="container">
    <%@include file="navbar.jsp" %>
    <div class="row mt-3 pl-2 pr-2 pt-3 border-top border-secondary">
    
            <h3>Welcome to <span class="app-name">Where-Is-My-Money</span></h3>
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">

                <p>If you want to start control your personal budget better, this App is for you!!!
                <p>Please, <a class="link-std" href="${pageContext.request.contextPath}/showMyLoginPage">login</a> 
                or <a class="link-std" href="${pageContext.request.contextPath}/register/showRegistrationForm">register</a> to start.
                <p>Thanks to <span id="logo">Where-Is-My-Money</span> you will be able to:  
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
                    <ul>
                        <li>Keep track of your expenses</li>
                        <li>Organize your monthly budget</li>
                        <li>Set money waste alert</li>
                        <li>Save more money</li>
                    </ul>
                </div>
            </div>
        </div>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/save.jpeg"
                     alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/10-money-lessons.jpg"
                     alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/money_problem.png"
                     alt="Third slide">
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
</div>

</body>
</html>
