<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<h1>Find a Stranger Mentor to ask questions</h1>
	
	<c:forEach var="myStranger" items="${myStrangers}">
	
		
		<c:out value="  ${myStranger.username}   "></c:out>
	
	
					<div class="navItem searchResultNav">
						
						<a href="${contextRoot}/profile/${myStranger.username}" class="sideATag">
							<img id="" class="resultPhoto" src="${profilePhoto}" onerror="this.onerror=null; this.src='/img/avatar.png'" alt="Profile Image" >	
							</a>
							
							
							<div class="resultUsername">
							
								<h3><a href="${contextRoot}/profile/${myStranger.username}" class="sideATag"><strong>${myStranger.username}</strong></a></h3>
															
								</div>
								
							<a class="chatButton" href="/askStranger">
								
								<img  src="/img/chatIcon.png" width="30"> 
							</a>
								
						</div>
	
	</c:forEach>

