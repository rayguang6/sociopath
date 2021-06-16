

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="img" value="/img" />
<c:url var="editProfileAbout" value="/edit-profile-about" />


<div class="container">


	<div class="profileContainer">
		<div class="profileHead">
			
				<img id="currentPhoto" class="profilePhoto" src="${img}/avatar${student.username}.png" onerror="this.onerror=null; this.src='${img}/avatar.png'" alt="Profile Image" >	
			
			<h1 class="profileName"><a href="${contextRoot}/profile/${student.username}">${student.username}</a></h1>
			
			
		
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
	<h1>Real Friends Of ${student.username} : </h1>
		<c:forEach var="realFriend" items="${realFriends}">	
		<div class="d-flex postHead ">
		
			<div class="postProfile">
				<img id="currentPhoto" class="profilePhoto" src="${img}/avatar${realFriend.key}.png" onerror="this.onerror=null; this.src='${img}/avatar.png'" alt="Profile Image" >	

			</div>
			
			<div>
				<h3><a href="/displayFriend/${realFriend.key}"> ${realFriend.key}</a></h3>
				<h5>His reputations toward ${student.username} : ${realFriend.value} </h5>
			</div>
		</div>
		</c:forEach>	
		
	</div>
	
	<div class="friendContainer">
	<h1>Frenemies Of ${student.username} : </h1>
		<c:forEach var="frenemy" items="${frenemies}">	
		<div class="d-flex postHead ">
		
			<div class="postProfile">
				<img id="currentPhoto" class="profilePhoto" src="${img}/avatar${frenemy.key}.png" onerror="this.onerror=null; this.src='${img}/avatar.png'" alt="Profile Image" >	

			</div>
			
			<div>
				<h3><a href="/displayFriend/${frenemy.key}"> ${frenemy.key}</a></h3>
				<h5>His reputations toward ${student.username} : ${frenemy.value} </h5>
			</div>
		</div>
		</c:forEach>	
		
	</div>

	<div class="friendContainer">
	<h1> ${student.username}'s point to other </h1>
		<c:forEach var="myFriendMap" items="${myFriendMaps}">	
		<div class="d-flex postHead ">
		
			<div class="postProfile">
				<img id="currentPhoto" class="profilePhoto" src="${img}/avatar${myFriendMap.key}.png" onerror="this.onerror=null; this.src='${img}/avatar.png'" alt="Profile Image" >	

			</div>
			
			<div>
				<h3><a href="/displayFriend/${myFriendMap.key}"> ${myFriendMap.key}</a></h3>
				<h5>${student.username}'s repution towards</h5> <h5> ${myFriendMap.key}: </h5>
				<c:out value="${myFriendMap.value}"></c:out>

			</div>
		</div>
		</c:forEach>	
		
	</div>
	
	

</div>