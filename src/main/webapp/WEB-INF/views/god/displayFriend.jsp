
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div class="container-lg ControllerContainer">

<div class="ControllerTitle">

	<h1> Display All Friends</h1>

</div>

<div class="ControllerBody">
	
	
	<c:forEach var="friend" items="${friends}">
	<a href = "displayFriend/${friend.key}">
			<c:out value="  ${friend.key}  "></c:out>
			</a>
			
			<c:out value="'s Friend:  ${friend.value}   "></c:out>
			<br><hr>
			
	</c:forEach>
	
	