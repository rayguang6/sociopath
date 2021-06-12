

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1>Event 4  Arrange Book </h1>


<form method="post" action="event4">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<label for="books">Enter the height of books, (each separate by a 'space')</label>
		<input type="text" name="books" placeholder="Enter Your Books">
		
		<input type="submit" value="Submit">
	
</form>