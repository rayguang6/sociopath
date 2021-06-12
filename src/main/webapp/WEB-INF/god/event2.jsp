

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1>Event 2  Chit Chat </h1>


<form method="post" action="event2">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<label for="s1"> Talker:</label>
		<select id="s1" name="s1">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
	 		 
		</select>
		
		<br>
		
		<label for="s2"> Listener:</label>
		<select id="s2" name="s2">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
	 		 
		</select>
		
		<br>
		
		<label for="s3"> Who Are They Talking About? </label>
		<select id="s3" name="s3">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
	 		 
		</select>
		
		<br>
	
		<label for="good">What are they talking about </label>
		<select id="good" name="good">

			<option value="true">Good Good Thing🥰</option>
	 		<option value="false">Bad Bad Thing 😔</option>
	 		
		</select>
		
		<input type="submit" value="Submit">
	
</form>