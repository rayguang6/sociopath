
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





<%@include file="/WEB-INF/includes/footer.jsp" %>