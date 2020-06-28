<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!-- <title>Login Page></title> -->
		<title><spring:message code="title.login.page"/></title>
		<link rel="stylesheet" href="${path}/webjars/bootstrap/3.3.5/css/bootstrap.min.css">
		<style type="text/css">
			body {
				background: url("http://quatangquangcao.net/wp-content/uploads/2016/02/AF-Client-Login-background-1024x682.png") no-repeat center center fixed;
				background-size: cover;
				display: flex;
				align-items: center;
				height: 100vh;
			}
		</style>
	</head>

	<body>
			<div class="container">
				<div class="row">
				
					<div class="col-md-4 col-md-offset-4">
						<div class="panel panel-default">
							<div class="panel-heading">
							
							<!-- 	<strong>Login To Your Account</strong> -->
								<strong><spring:message code="label.login.header"/></strong>
								
								<a href="${path}/user/register" class="btn btn-outline-secondary btn-sm pull-right">
									<strong><i class="fa fa-user-plus"></i> <spring:message code="label.register.header"/></strong>
								</a>
					
							</div>
							<div class="panel-body">
							<form method="post" action="${path}/user/login" class="form-signin form-horizontal">

								<c:if test="${error != null}">
									<div class="alert alert-danger">
										<strong>${error}</strong>
									</div>
								</c:if>
								<c:if test="${message != null}">
									<div class="alert alert-success">
										<strong>${message}</strong>
									</div>
								</c:if>

					<div class="form-group">
									<div class="col-md-12">
										<div class="input-group input-group-md">
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-user"></span>
											</span>
											<spring:message code="placeholder.username" var="usernamePlaceholder"/>
											<!-- <input type="text" class="form-control" name="username" placeholder="Enter Username"/> -->
											<input type="text" class="form-control" name="username" placeholder="${usernamePlaceholder}"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<div class="input-group input-group-md">
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-lock"></span>
											</span>
											<spring:message code="placeholder.password" var="passwordPlaceholder"/>
											<!-- <input type="password" class="form-control" name="password" placeholder="Enter Password" /> -->
											<input type="password" class="form-control" name="password" placeholder="${passwordPlaceholder}" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-md-12">
										<div class="checkbox">
											<label>
												<input type="checkbox" name="remember-me" id="remember-me"/> <!-- Remember Me --> <spring:message code="label.remember.me"/>
											</label>
										</div>
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<div class="form-group">
									<div class="col-md-12">
										<button type="submit" class="btn btn-default btn-block">
											<span class="glyphicon glyphicon-log-in"></span> <!-- Login --><spring:message code="label.button.login"/>
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>