<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container">
	
	<div class="eventContainer">
	
	<div class="eventHead">
		<h1> Arrange Book</h1>
	</div>
	
		<div class="eventBody">


	<form method="post" action="ArrangeBook">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<label for="books">Enter the height of books, (each separate by a 'space')</label>
		<input type="text" name="books" placeholder="Enter Your Books">
		
		<input type="submit" value="Submit">
	
</form>


</div></div></div>