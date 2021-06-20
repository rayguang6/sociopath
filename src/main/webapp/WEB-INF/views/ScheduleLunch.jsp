<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<div class="container">
	
	<div class="eventContainer">
	
	<div class="eventHead">
		<h1>Schedule Lunch</h1>
	</div>
	
		<div class="eventBody">


<form method="post" action="ScheduleLunch">

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

	<div class="form-check">

		<h5>Select Students To Observe</h5>
		<c:forEach var="student" items="${students}">
			
			
			<c:choose>
			    <c:when test="${student.username==thisUser}">
			    	
			    </c:when>    
			    <c:otherwise>
			        <input class="form-check-input" type="checkbox" name="s1" value="${student.username}">${student.username} <br>
			    </c:otherwise>
			</c:choose>
			
			
		</c:forEach>


	</div>


	</select> <br> <label for="day">How many day you want to Observe?</label> <input type="text"
		name="day" placeholder="days"> <br>
	<hr>
	<br> <input type="submit" value="Submit">

</form>

</div></div></div>