<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<%@include file="head.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/login-page-style.css">
</head>

<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div id="register-card" class="card">
				<form:form
					action="${pageContext.request.contextPath}/register/processRegistrationForm"
					modelAttribute="crmUser" class="form-horizontal">
					<div class="card-header">
						<h2 id="logo">Where-Is-My-Money</h2>
						<h2>Sign up</h2>
						<c:if test="${registrationError != null}">
							<div class="alert alert-danger col-xs-offset-1 col-xs-10">
								${registrationError}
								<form:errors path="*" />
							</div>
						</c:if>
					</div>
					<div class="card-body">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<form:input path="userName" placeholder="username (*)"
								class="form-control" />
						</div>

						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="far fa-user"></i></span>
							</div>
							<form:input path="firstName" placeholder="first name (*)"
								class="form-control" />
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="far fa-user"></i></span>
							</div>
							<form:input path="lastName" placeholder="last name (*)"
								class="form-control" />
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-at"></i></span>
							</div>
							<form:input path="email" placeholder="email (*)"
								class="form-control" />
						</div>
						<div class="form-group">
							<input type="submit" class="btn float-right login_btn"
								value="Add" />
							<button onclick="goBack()" type="button"
								class="btn float-left back_btn">Back</button>
						</div>
					</div>
				</form:form>

				<form:form>
					<div class="card-body">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<form:password path="password"
								placeholder="Enter old password (*)" class="form-control" />
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>

							<form:password path="matchingPassword"
								placeholder="Enter new password (*)" class="form-control" />
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>

							<form:password path="matchingPassword"
								placeholder="Confirm new password (*)" class="form-control" />
						</div>
						<div class="form-group">
							<input type="submit" class="btn float-right login_btn"
								value="Change password"/>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>