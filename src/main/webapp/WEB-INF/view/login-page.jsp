<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<%@include file="head.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/login-page-style.css">
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h2 id="logo">Where-Is-My-Money</h2>
					<h2>Sign In</h2>

				</div>
				<div class="card-body">
					<form
						action="${pageContext.request.contextPath}/authenticateTheUser"
						method="POST">
						<!-- error message -->
						<div class="form-group">
							<div class="col-xs-15">
								<div>
									<c:if test="${param.error != null}">
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											Invalid username or password.</div>
									</c:if>
									<c:if test="${registrationSuccess != null}">
										<div class="alert alert-success col-xs-offset-1 col-xs-10">
											${registrationSuccess}</div>
									</c:if>
									<c:if test="${param.logout != null}">
										<div class="alert alert-success col-xs-offset-1 col-xs-10">
											You have been logged out.</div>
									</c:if>
									
								</div>
							</div>
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" name="username" placeholder="username"
								class="form-control">

						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" name="password" class="form-control"
								placeholder="password">
						</div>
						<div class="form-group">
							<input type="submit" value="Login"
								class="btn float-right login_btn">

							<button onclick="goBack()" type="button"
								class="btn float-left back_btn">Back</button>
						</div>

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
				<div class="card-footer">
					<div class="d-flex justify-content-center">
						Don't have an account? <a class="link-std"
							href="${pageContext.request.contextPath}/register/showRegistrationForm">
							Sign Up</a>
					</div>
					<!-- <div class="d-flex justify-content-center">
						<a href="#">Forgot your password?</a>
					</div> -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>