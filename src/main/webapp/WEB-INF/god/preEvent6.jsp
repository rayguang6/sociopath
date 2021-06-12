

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>





<h1>Event 6 </h1>

<form method="post" action="preEvent6">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
		<label for="numOfRelation">How Many Relationships You Want To Add</label>
		<input type='text' name='numOfRelation'>
		
		<input class="btn-primary" type="submit" value="Submit">
	
</form>