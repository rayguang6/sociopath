

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<h1>Hello Result of Event 6</h1>
	
	
	<h5>Friends</h5>
	

	
	<br>
	
	
	
	<c:forEach var="friendshipList" items="${friendshipLists}" varStatus="s">
			<c:out value="${s.index+1} "></c:out>
			<c:out value="  ${friendshipList}    "></c:out>
			
			<br>
			
	</c:forEach>
	
	
	