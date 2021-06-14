<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />


<!-- Required meta tags -->
<base href="/">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>



<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!-- Bootstrap CSS -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextRoot}/css/register.css" rel="stylesheet">

<script src="${contextRoot}/js/connectionmanager.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>



</head>
<body>

	<div id="mainContainer">

		<div id="topContainer">


			<h1 class="text-center text-white loginTitle">Register Now</h1>

			<c:url var="loginUrl" value="/login" />


			<div class="loginContainer">




				<main class="form-signin"> <form:form method="post"
					modelAttribute="user" class="login-form">

					
					<div class="loginFormText">
						<h1 class="h1 mb-3 " id="loginText">Sign Up</h1>
						<h6 class="h6 mb-3 fw-normal" id="JoinSociopath">Join Sociopath Now!</h6>
					</div>


					<div class="input-group inputText">
						<form:input type="text" path="username" placeholder="Username"
							class="form-control" />
					</div>

					<div class="input-group inputText">
						<form:input type="password" path="password" placeholder="Password"
							class="form-control" />
					</div>

					<div class="input-group inputText">
						<input type="password" name="repeatpassword"
							placeholder="Repeat password" class="form-control" />
					</div>

					<div class="input-group">
						<button class="w-75 btn btn-lg btn-primary loginButton" type="submit" value="Submit">Register</button>
					</div>

					<h6 class="h6 mb-3 fw-normal" id="registerLink">
						Already Have An Account?<a href="${contextRoot}/login"> Sign
							In Now</a>
					</h6>

				</form:form> </main>

			</div>







			<script>
				document.onload = function() {
					document.getElementById('#usernameInput').autofocus();
				};
			</script>

			<%@include file="/WEB-INF/includes/footer.jsp"%>