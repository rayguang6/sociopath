<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Ask Stranger</h1>

<c:out value="How is ${teacher}'s Teaching"></c:out>


<form method="post" action="askStranger">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		
		<input type="hidden" name="mentee" value="${student}">
		<input type="hidden" name="mentor" value="${teacher}">
		<br>
	
		<label for="rep_point">Teaching Experience> </label>
		<select id="rep_point" name="rep_point">

			<option value="10">Awesome</option>
	 		<option value="2">Too Bad </option>
	 		
		</select>
		
		<input type="submit" value="Submit">
	
</form>