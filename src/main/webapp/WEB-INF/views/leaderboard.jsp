
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
						<li class="list-group-item"><strong><c:out
									value="  ${reputationBoard.key}  -> "></c:out></strong> <c:out
								value="Point:  ${reputationBoard.value}   "></c:out></li>
					</c:forEach>
				</ul>

			</div>
			
		

	</div>
</div>


