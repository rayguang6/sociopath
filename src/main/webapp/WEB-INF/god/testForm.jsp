

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h1>Test Form</h1>


<form method="post" action="testForm">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<input type="text" name="source" id="source">
		<input type="text" name="destination" id="destination">
		
		<input type="submit" value="Submit">
	
</form>