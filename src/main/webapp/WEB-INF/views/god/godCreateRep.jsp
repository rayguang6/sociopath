<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1>God Add Relation</h1>


<form method="post" action="godCreateRep">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<label for="student1">From Student:</label>
		<select id="student1" name="student1">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
		
		<label for="student2">To Student:</label>
		<select id="student2" name="student2">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
	
		<input type="text" name="rep_point" id="rep_point">
	
		<input type="submit" value="Submit">
	
</form>