
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<h1>Display All Friends</h1>
	
	
	<h5>Friends</h5>
	
	<c:forEach var="myfriend" items="${myfriends}">
	
		<c:out value="  ${myfriend.username}   "></c:out>
	
	</c:forEach>
	
	<br>
	
	
	
	<c:forEach var="friend" items="${friends}">
			<c:out value="  ${friend.key}   "></c:out>
			
			<c:out value="Friends:  ${friend.value}   "></c:out>
			<br>
			
	</c:forEach>
	
	
	<br><br><br><br><br><br><br><br><br>
	
	<h1>Get All Friends</h1>
	
	<br><br><br>
	
	<c:forEach var="friendMaps" items="${friendMaps}">
	
		
			<c:out value="  ${key.username}   "></c:out>
		
		
			<c:out value="Friends:  ${value.username}   "></c:out>
		
			
			<br>
			
	</c:forEach>
