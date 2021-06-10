

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>God View Profile</h1>


<c:forEach var="student" items="${students}">

	<c:url var="profilePhoto" value="/profilephoto/${student.id}" />
	<c:url var="profileLink" value="/profile/${student.id}" />
	<c:url var="chatviewLink" value="/chatview/${student.id}" />
	
	
	<c:url var="deleteLink" value="/godDelete?id=${student.id}" />
	<c:url var="deleteStudent" value="/godDeleteStudent?username=${student.username}" />
	<div class="row person-row">
		<div class="col-md-12">

			<div class="results-details">
			
				<div class="student basic profile">
					<a href="${profileLink}"><c:out value="${student.username}" /></a>
					
					<c:out value="id: ${student.id}"></c:out>
					<c:out value="Rep: ${student.reputation}"></c:out>
					<c:out value="${student.divingrate}"></c:out>
					<c:out value="${student.friendList}"></c:out>
					
					<a onclick="confirm('Sure Want Delete?')" href="${deleteStudent}">Delete</a>
				</div>


				<div class="lunch">
				
					
					
				</div>
				
			</div>

		</div>
	</div>

</c:forEach>


<c:url var="loginUrl" value="/login" />

<div class="statusContainer">
	<div class="mb-3">

		<form:errors path="email" />
		<form:errors path="password" />

		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title">Create student</div>
			</div>


			<div class="panel-body">
				<form:form method="post" modelAttribute="user" class="login-form">
				

					<div class="input-group">
						<form:input type="text" id="unameInput" path="username" placeholder="Username"
							class="form-control" />
					</div>

					<div class="input-group">
						<form:input type="hidden" path="password" value="sociopath"
							class="form-control" />
					</div>
					
					<div class="input-group">
						<button type="submit" class="statusButton">Add Now</button>
					</div>

				</form:form>
			</div>
		</div>

	</div>



</div>

<script>
document.onload = function() {
 document.getElementById('unameInput').focus();
};
</script>