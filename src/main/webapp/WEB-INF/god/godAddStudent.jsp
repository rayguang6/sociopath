<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
						<form:input type="text" path="username" placeholder="Username"
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