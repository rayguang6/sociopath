<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<div class="container-lg ControllerContainer">

<div class="ControllerTitle">

	<h1> Reputation Controller</h1>

</div>

<div class="ControllerBody">
	


<form method="post" action="godCreateRep" class="controlForm">
	<h3>Create Reputation</h3>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<label for="student1">From Student:</label>
		<select id="student1" name="student1" class="btn btn-mini">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
		
		<label for="student2">To Student:</label>
		<select id="student2" name="student2" class="btn btn-mini">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
	
		<input type="number" name="rep_point" id="rep_point" class="form-control-sm">
	
		<input type="submit" value="Create" class="btn btn-primary">
	
</form>

<form method="post" action="godUpdateRep" class="controlForm">
	<h3>Update Reputation</h3>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<label for="student1">From Student:</label>
		<select id="student1" name="student1" class="btn btn-mini">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
		
		<label for="student2">To Student:</label>
		<select id="student2" name="student2" class="btn btn-mini">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
	
		<input type="number" name="rep_point" id="rep_point" class="form-control-sm">
	
		<input type="submit" value="Update" class="btn btn-primary">
	
</form>



<form method="post" action="godDeleteRep" class="controlForm">
	<h3>Delete Reputation</h3>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<label for="student1">From Student:</label>
		<select id="student1" name="student1" class="btn btn-mini">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
		
		<label for="student2">To Student:</label>
		<select id="student2" name="student2" class="btn btn-mini">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
	
		<input type="submit" value="Delete" class="btn btn-primary">
	
</form>


<form method="post" action="godDeleteBothRep" class="controlForm">
	<h3>Delete Both Rep</h3>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<label for="student1">From Student:</label>
		<select id="student1" name="student1" class="btn btn-mini">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
		
		<label for="student2">To Student:</label>
		<select id="student2" name="student2" class="btn btn-mini">
		  
		  	<c:forEach var="student" items="${students}">
		 		 <option value="${student.username}">${student.username}</option>
	 		 </c:forEach>
		</select>
	
		<input type="submit" value="Delete" class="btn btn-primary">
	
	</form>
		
		
		<form method="post" action="godDeleteAllRep" class="controlForm">
		<h3>Delete All Reputations in Graph</h3>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="DELETE ALL" onclick="confirm('Are You Sure You Want To Delete All Reputations In Database?')" class="btn btn-primary">
	</form>
	
	<form method="post" action="createBasicRep" class="controlForm">
	<h3>Reset To Default Reputation Relationship</h3>
	<img src="/img/basicRep.png" width="200">
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="RESET" onclick="confirm('Are You Sure You Want To Reset To Default Reputations?')" class="btn btn-primary">
	</form>



		
	</div>
</div>