<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Event 1  Teach Stranger</h1>

<form method="post" action="event1">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<label for="mentee"> Student:</label>
		<select id="mentee" name="mentee">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
	 		 
		</select>
		
		<br>
		
		<label for="mentor">Teacher :</label>
		<select id="mentor" name="mentor">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
	 		 
		</select>
		
		<br>
	
		<label for="rep_point">Teaching Experience: </label>
		<select id="rep_point" name="rep_point">

			<option value="10">Awesome</option>
	 		<option value="2">Too Bad </option>
	 		
		</select>
		
		<input type="submit" value="Submit">
	
</form>