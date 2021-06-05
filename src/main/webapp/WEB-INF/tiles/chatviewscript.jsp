<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
  <c:url var="outboundDestination" value="/app/message/send/${chatWithUserID}" />  
  <c:url var="inboundDestination" value="/user/queue/${chatWithUserID}" />
    <script>

 	function newMessageCallback(message){
			console.log("Message Received" , JSON.parse(message.body).text);
			alert(JSON.parse(message.body).text);
 	 	}
   	
    connectionManager.addSubscription("${inboundDestination}",newMessageCallback);
    
    function sendMessage() {
    	
    	var text = $("#chat-message-text").val();

		alert(text);

		var message = {
					'text' : text
				};

		connectionManager.send("${outboundDestination}",message);
		
    	$("#chat-message-text").val("");
    	$("#chat-message-text").focus();
    }
    
	$(document).ready(function() {
		
		$(document).keypress(function(e) {
			if(e.which == 13) {
				
				// Enter key pressed
				sendMessage();
				
				return false;
			}
		});
		
		$('#chat-send-button').click(function(){
			sendMessage();
		});
	});
	
    </script>