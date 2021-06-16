<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Chit Chat</h1>

<c:out value="Chatting With ${thisFriend} Now"></c:out><br>


<form method="post" action="ChitChat">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<input type="hidden" name="s1" value="${me}">
		<input type="hidden" name="s2" value="${thisFriend}">
		
		<label for="s3"> Who You Want To Talking About? </label>
		<select id="s3" name="s3">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
	 		 
		</select>
		
		<br>
	
		<label for="good">What you want to talk about this student? </label>
		<select id="good" name="good">

			<option value="true">Good Good Thing</option>
	 		<option value="false">Bad Bad Thing </option>
	 		
		</select>
		
		<input type="submit" value="Submit">
	
</form>




