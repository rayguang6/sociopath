
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
<link href="${contextRoot}/css/styles.css" rel="stylesheet">

<script src="${contextRoot}/js/connectionmanager.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>





<!-- Web Socket -->
 <c:url var="outboundDestination" value="/app/message/send/${chatWithUserID}" />  
  <c:url var="inboundDestination" value="/user/queue/${chatWithUserID}" />
  <c:url var="conversationAjaxUrl" value="/conversation/${chatWithUserID}" />

   
<script>
		function alertUser(from, text) {
			if (!("Notification" in window)) {
				// Notifications not supported
				return;
			} else if (Notification.permission === "denied") {
				// User doesn't want notifications
				return;
			} else if (Notification.permission !== "granted") {
				Notification.requestPermission();
			}

			if (Notification.permission === "granted") {
				var notification = new Notification(from, {
					body : text
				});

				notification.onclick = function() {
					window.location.href = "/messages?p=1";
				}
			}
		}
		var connectionManager = new ConnectionManager("/chat");
		connectionManager.addSubscription("/user/queue/newmessages", function(
				messageJson) {
			var message = JSON.parse(messageJson.body);
			alertUser(message.from, message.text);
		});
	</script>






<title>Sociopath</title>
</head>
<body>


	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">

	<div class="container-fluid">

		<a class="navbar-brand brandLogo" href="${contextRoot}/dashboard"><strong>SCP</strong></a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			
				<li class="nav-item leftItem"><a class="nav-link active"
					aria-current="page" href="${contextRoot}/leaderboard">Leaderboard</a>
				</li>

				<li class="nav-item leftItem"><a class="nav-link active"
					aria-current="page" href="${contextRoot}/viewstatus">Announcements</a>
				</li>
			</ul>


			<form class="d-flex searchForm" action="search">
			
			<span class="d-flex seachBar ">
			
				<input class="form-control searchInput" type="search" placeholder="Search"
					aria-label="Search" name="s">
					
					<button class="searchButton" value="submit">
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="grey" class="bi bi-search searchIcon" viewBox="0 0 16 16">
 						 <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
						</svg>
					
					</button>
			

			</span>
			</form>
		</div>




<!-- RIGHT  -->
		<ul class="navbar-nav me-auto mb-2 mb-lg-0">


			<sec:authorize access="isAuthenticated()">
			
			
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				
					<li class="nav-item leftItem"><a class="nav-link active"
						aria-current="page" href="${contextRoot}/profile">
						<c:out value="${loggedIn}'s"></c:out>
						 Profile</a>
					</li>
	
				
				</ul>
				
			</div>
			
			
			

				<sec:authorize access="hasRole('ADMIN')">

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							ADMIN Action </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item"
								href="${contextRoot}/viewstatus">View status</a></li>
							<li><a class="dropdown-item" href="${contextRoot}/addstatus">Post
									Status</a></li>
							<li><hr class="dropdown-divider"></li>

							<li><a class="dropdown-item"
								href="${contextRoot}/godDashboard">GOD Dashboard</a></li>
							<li><a class="dropdown-item"
								href="${contextRoot}/godCreateRep">Create Rep</a></li>
							<li><a class="dropdown-item"
								href="${contextRoot}/godCreateFriend">Create Friend</a></li>
							<li><a class="dropdown-item"
								href="${contextRoot}/displayReputation">Display Reputation</a></li>
							<li><a class="dropdown-item"
								href="${contextRoot}/displayFriend">Display Friend</a></li>
							<li><a class="dropdown-item" href="${contextRoot}/testForm">testForm</a></li>
							<li><a class="dropdown-item" href="${contextRoot}/testForm2">testForm
									2 </a></li>

							<li><hr class="dropdown-divider"></li>

							<li><a class="dropdown-item" href="${contextRoot}/event1">Event
									1</a></li>
							<li><a class="dropdown-item" href="${contextRoot}/event2">Event
									2</a></li>
							<li><a class="dropdown-item" href="${contextRoot}/event3">Event
									3</a></li>
							<li><a class="dropdown-item" href="${contextRoot}/event4">Event
									4</a></li>
							<li><a class="dropdown-item" href="${contextRoot}/event5">Event
									5</a></li>
							<li><a class="dropdown-item" href="${contextRoot}/preEvent6">Event
									6</a></li>


						</ul></li>

				</sec:authorize>


				<c:url var="logoutLink" value="/logout" />
				<form id="logoutForm" method="post" action="${logoutLink}">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button class="myButton" type="submit">Logout</button>
				</form>


			</sec:authorize>

			<sec:authorize access="!isAuthenticated()">

				<li class="nav-item"><a class="nav-link"
					href="${contextRoot}/login">Login</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${contextRoot}/register">Register</a></li>

			</sec:authorize>

		</ul>

	</div>

	</nav>

	<div id="mainContainer">

		<div id="topContainer">







<!--     ######################################################################################### -->
<!--  ABOVE ARE Just HEADER without the sidebar-->




<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="img" value="/img" />
<c:url var="editProfileAbout" value="/edit-profile-about" />


<div class="container">
	
	<div class="profileContainer">
		<div class="profileHead">
			
				<img id="currentPhoto" class="profilePhoto" src="${img}/avatar${student.username}.png" onerror="this.onerror=null; this.src='${img}/avatar.png'" alt="Profile Image" >	
			
			<h1 class="profileName"><a href="${contextRoot}/profile/${student.username}">${student.username}</a></h1>
			
			<div class="profileRep">
				<h3>Reputations: 100 REP</h3>
			</div>
			
		
		</div>
		<div class="profileBody">
			
		<ul class="list-group list-group-flush">
		  <li class="list-group-item"><strong>ID:</strong> ${student.id} </li>
		  <li class="list-group-item"><strong>Diving Rate:</strong> ${student.divingrate}% </li>
		  <li class="list-group-item"><strong>BIO:</strong> ${student.about}</li>
		</ul>
		
		</div>
	</div>


<div class="friendContainer">
	<h1>Friends Of ${student.username} : </h1>
	<h3>Total Friends: </h3>
	
	<div class="allFriends">
			
		<div class="d-flex postHead ">
		
			<div class="postProfile">
				<img id="currentPhoto" class="profilePhoto" src="${img}/avatar${student.username}.png" onerror="this.onerror=null; this.src='${img}/avatar.png'" alt="Profile Image" >	

			</div>
			
			<div>
				<h3><a href="#"> Lei Zhi Guang </a></h3>
				<h5>Reputations: 1000 REP</h5>
				<p> About: </p>
			</div>
		
		
		</div>
		
		</div>
	
	
</div>

</div>