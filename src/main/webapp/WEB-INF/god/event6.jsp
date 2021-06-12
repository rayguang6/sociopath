

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1>Event 6 </h1><br>

<c:out value="You've entered to create  ${numOfRelation}  Friendships"></c:out>

<h3>Enter The Student You Want To Create</h3>

<form method="post" action="event6">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<input type="hidden" name="numOfRelation" value="${numOfRelation}" />
		
		
		<c:forEach begin="1" end="${numOfRelation}" var="i">
			<c:out value="${i}) "></c:out>
			<input type="text" name="s1"/>
		  	<input type="text" name="s2"/>
		  	<br>
		</c:forEach>
		
		<input class="btn-primary" type="submit" value="Submit">
	
</form>