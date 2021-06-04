<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="search" value="/search" />



<div class="statusContainer">
	<div class="mb-3">

		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title">
					
				</div>
			</div>

			<div class="panel-body">
				<div class="homepage-status">${statusUpdate.text}</div>
			</div>


		</div>

	</div>
</div>

<div class="row">
	<div class="col-md-8 col-md-offset-2">

		<form action="${search}" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<div class="input-group input-group-lg">
			
				<input type="text" class="form-control" name="s" placeHolder="Search Hobbies">
	
				<span class="input-group-btn">
					<button id="search-button" class="btn btn-primary" type="submit">
					Find People
					</button>
				</span>			
			</div>

		</form>


	</div>
</div>