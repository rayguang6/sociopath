<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<div class="container">

	<div class="leaderboardContainer ">
	
			<h3>Find a Stranger Mentor to ask questions</h1><br>
			<h5 class="leaderTitle">List Of Your Strangers</h1>			
		
			<div class="leaderBody">

		
					<c:forEach var="myStranger" items="${myStrangers}">
					
					<c:url var="profilePhoto" value="/img/avatar${myStranger.username}.png" />	
					
					<div class="sideDiv">
						
						<div class="navItem searchResultNav">
						
						<a href="${contextRoot}/profile/${myStranger.username}" class="sideATag">
							<img id="" class="resultPhoto" src="${profilePhoto}" onerror="this.onerror=null; this.src='/img/avatar.png'" alt="Profile Image" >	
							</a>
							
							
							<div class="resultUsername">
							
								<h3><a href="${contextRoot}/profile/${myStranger.username}" class="sideATag"><strong>${myStranger.username}</strong></a></h3>
							
								</div>
								
							<a class="chatButton"  href="/FindStranger/${myStranger.username}">
								<img  src="/img/chatIcon.png" width="30"> 
							</a>
								
						</div>
						
					</div>				
									
					</c:forEach>
		

			</div>
			
		

	</div>
</div>
