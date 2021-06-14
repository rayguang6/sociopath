
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>




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


			<h1 class="text-center text-white loginTitle">Welcome To Sociopath</h1>

			<c:url var="loginUrl" value="/login" />


			<div class="loginContainer">
			
				<c:if test="${param.error != null}">
					<div class="loginError">Incorrect username or password.</div>
				</c:if>

				<main class="form-signin">
				<form method="post" action="${loginUrl}" class="login-form">

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
						
					
					<div class="loginFormText">
						<h1 class="h1 mb-3 " id="loginText">Log In</h1>
						<h6 class="h6 mb-3 fw-normal" id="JoinSociopath">Connect Now!</h6>
					</div>
				
					
					
					<div class="input-group inputText">
						<input type="text" class="form-control" id="usernameInput"
							placeholder="Username" name="username"> 
					</div>
					
					
					<div class="input-group inputText">
						<input type="password" name="password" class="form-control" id="Password"
							placeholder="Password"> 
					</div>
					

					<div class="input-group">
						<button class="w-75 btn btn-lg btn-primary loginButton" type="submit" value="Submit">Sign
						in</button>
					</div>
					
					
					<h6 class="h6 mb-3 fw-normal" id="registerLink">Don't Have Account Yet?<a href="${contextRoot}/register"> Create An Account</a></h6>

				</form>
				</main>

			</div>







			<script>
				document.onload = function() {
					document.getElementById('#usernameInput').autofocus();
				};
			</script>

			<%@include file="/WEB-INF/includes/footer.jsp"%>