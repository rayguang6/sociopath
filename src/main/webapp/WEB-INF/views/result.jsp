
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<div class="leaderboardContainer ">
	

			<h1 class="leaderTitle">Search Result</h1>			
		
			<div class="leaderBody">

		
					<c:forEach var="result" items="${results}">
					
					<c:url var="profilePhoto" value="/img/avatar${result.username}.png" />	
					
					<div class="sideDiv">
						
						<div class="navItem searchResultNav">
						
						<a href="${contextRoot}/profile/${result.username}" class="sideATag">
							<img id="" class="resultPhoto" src="${profilePhoto}" onerror="this.onerror=null; this.src='/img/avatar.png'" alt="Profile Image" >	
							</a>
							
							
							<div class="resultUsername">
							
								<h3><a href="${contextRoot}/profile/${result.username}" class="sideATag"><strong>${result.username}</strong></a></h3>
								<h6><a href="#">Java Spring</a></h6>
							
								</div>
								
							<a class="chatButton" href="/chat/${id}">
								<img  src="/img/chatIcon.png" width="30"> 
							</a>
								
						</div></a>
						
					</div>				
									
					</c:forEach>
		

			</div>
			
		

	</div>
</div>


