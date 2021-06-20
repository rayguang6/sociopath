
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="row">
	<div class="col-md-12">

		<div class="panel panel-default">

			<div class="panel panel-heading">
				<div class="panel-title">Chatting with <c:out value="${chatWithUserName}" /></div>
			</div>

			<div class="panel-body">
			
				<div id="chat-message-view">
					
					<div id="chat-message-previous">
						<a id="chat-older-messages" href="#">View older messages</a>
					</div>
				
				
					<div id="chat-message-record"></div>
					
					
					<div id="chat-message-send" class="input-group input-group-lg">
					
						<textarea class="form-control" id="chat-message-text" placeholder="Enter message"></textarea>
						
						<span class="input-group-btn">
							<button id="chat-send-button" class="btn btn-primary" type="button">Send</button>
						
						</span>
					</div>
				</div>
			
			</div>


		</div>

	</div>


</div>


<!-- Chit Chat 
<form method="post" action="ChitChat">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<input type="hidden" name="s1" value="${me}">
		<input type="hidden" name="s2" value="${chatWithUserName}">
		
		<label for="s3"> Who You Want To Talking About? </label>
		<select id="s3" name="s3">
		  
		  	<c:forEach var="student" items="${students}">	 		 
		  	 
	 		 <c:choose>
			    <c:when test="${student.username==chatWithUserName}">
			    	
			    </c:when>    
			    <c:otherwise>
			        <option value="${student.username}">${student.username}</option>
			    </c:otherwise>
			</c:choose>
	 		 </c:forEach>
	 		 
	 		 
		</select>
		
		<br>
	
		<label for="good">What you want to talk about this student? </label>
		<select id="good" name="good">

			<option value="true">Good Good Thing</option>
	 		<option value="false">Bad Bad Thing </option>
	 		
		</select>
		
		<input type="submit" value="Submit">
	
</form>

-->

<%@include file="/WEB-INF/includes/footer.jsp" %>