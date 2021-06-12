

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1>Event 3 Have Lunch</h1>
<h3>>>Your Road To Glory >></h3>
<h3>>> PARALLEL FARMING >></h3>

<form method="post" action="event3">

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

	<div class="form-check">

		<h5>Select Students To Observe</h5>
		<c:forEach var="student" items="${students}">
			<input class="form-check-input" type="checkbox" name="s1" value="${student.username}">${student.username} <br>
		</c:forEach>


	</div>


	<label for="s2"> Who Are 'ME' :</label> <select id="s2" name="s2">

		<c:forEach var="student" items="${students}">
			<option value="${student.username}">${student.username}</option>
		</c:forEach>

	</select> <br> <label for="day">How many day </label> <input type="text"
		name="day" placeholder="days"> <br>
	<hr>
	<br> <input type="submit" value="Submit">

</form>