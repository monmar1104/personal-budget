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
			<div class="row">
				<form:form
					action="${pageContext.request.contextPath}/user/updateUser"
					modelAttribute="user" class="form-horizontal" method="post">
					<div class="card-header">
						<h2 id="logo">Where-Is-My-Money</h2>
						<h2>Sign up</h2>
						<c:if test="${registrationError != null}">
							<div class="alert alert-danger col-xs-offset-1 col-xs-10">
								${registrationError}
								<form:errors path="*" />
							</div>
						</c:if>
						<c:if test="${changePasswordSuccess != null}">
							<div class="alert alert-success col-xs-offset-1 col-xs-10">
								${changePasswordSuccess}</div>
						</c:if>
						<c:if test="${oldPasswordError != null}">
							<div class="alert alert-danger col-xs-offset-1 col-xs-10">
								${oldPasswordError}
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
								value="Save" />
							<button onclick="${pageContext.request.contextPath}/" type="button"
								class="btn float-left back_btn">Back</button>
						</div>
					</div>
				</form:form>
				</div>
				
				<hr style="color:white">
				<div class="row">
				<div class="col-2 align-bottom justify-content-end">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#changePassword" accesskey="7">Change
						password</button>
				</div>
				</div>



				<!-- Modal -->
				<div class="modal fade" id="changePassword" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Change
									password</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form:form
									action="${pageContext.request.contextPath}/user/changePassword"
									modelAttribute="password" class="form-horizontal" method="post">
									<div class="card-body">

										<div class="input-group form-group">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-key"></i></span>
											</div>
											<form:password path="oldPassword"
												placeholder="Enter old password (*)" class="form-control" />
										</div>
										<div class="input-group form-group">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-key"></i></span>
											</div>

											<form:password path="newPassword"
												placeholder="Enter new password (*)" class="form-control" />
										</div>
										<div class="input-group form-group">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-key"></i></span>
											</div>

											<form:password path="matchingNewPassword"
												placeholder="Confirm new password (*)" class="form-control" />
										</div>
										<div class="form-group">
											<input type="submit" class="btn float-right login_btn"
												value="Change" />
												<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Close</button>
										</div>
									</div>
								</form:form>
							</div>

						</div>
					</div>
				</div>


				<%--end of modal --%>


			</div>
		</div>
	</div>
</body>
</html>