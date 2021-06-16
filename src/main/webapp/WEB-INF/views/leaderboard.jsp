
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<div class="leaderboardContainer ">
	

			<h1 class="leaderTitle">The REPUTATION Ranking</h1>


			<h5 class="leaderTitle">REP Leader Board</h5>
			
		
			<div class="leaderBody">

				<ul class="list-group list-group-flush">
					<c:forEach var="reputationBoard" items="${reputationBoards}">
						<li class="list-group-item">
						
						<a href="${contextRoot}/profile/${reputationBoard.key}" class="sideATag">
							<img id="" class="resultPhoto" src="img/avatar${reputationBoard.key}.png" onerror="this.onerror=null; this.src='/img/avatar.png'" alt="Profile Image" >	
							</a>
						
						<strong>
						
						<c:out value="  ${reputationBoard.key}  -> "></c:out></strong> 
									
						<c:out value="Point:  ${reputationBoard.value}   "></c:out></li>
					</c:forEach>
				</ul>

			</div>
			
		

	</div>
</div>


