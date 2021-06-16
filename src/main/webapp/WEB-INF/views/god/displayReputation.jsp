
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container-lg ControllerContainer">

<div class="ControllerTitle">

	<h1> Display All Reputation </h1>

</div>

<div class="ControllerBody">

<c:forEach var="friend" items="${friends}">

	<c:out value="  ${friend.username}   "></c:out>

</c:forEach>

<c:forEach var="student" items="${students}">

	<c:url var="deleteLink" value="/godDelete?id=${student.id}" />
	<c:url var="deleteStudent" value="/godDeleteStudent?username=${student.username}" />
	
	<div class="row person-row">
		<div class="col-md-12">

			<div class="results-details">
			
				<div class="student basic profile">
					<a href="/profile/${student.username}"><c:out value="${student.username}" /></a>
					
		
					<c:out value="   ${student.reputationList}"></c:out>

										
				</div>

			</div>

		</div>
	</div>

</c:forEach>


<c:url var="loginUrl" value="/login" />

</div>
</div>
