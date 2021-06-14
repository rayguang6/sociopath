<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/chatviewscript.jsp" %>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>


<sec:authorize access="isAuthenticated()">

	<c:url var="webSocketEndpoint" value="/chat" scope="request" />
	<c:url var="notificationQueue" value="/user/queue/newmessages" />
	<c:url var="notificationUrl" value="/messages?p=1" />

	<script>
		function alertUser(from, text) {
			if (!("Notification" in window)) {
				// Notifications not supported
				return;
			} else if (Notification.permission === "denied") {
				// User doesn't want notifications
				return;
			} else if (Notification.permission !== "granted") {
				Notification.requestPermission();
			}

			if (Notification.permission === "granted") {
				var notification = new Notification(from, {
					body : text
				});

				notification.onclick = function() {
					window.location.href = "${notificationUrl}";
				}
			}
		}
		var connectionManager = new ConnectionManager("${webSocketEndpoint}");
		connectionManager.addSubscription("${notificationQueue}", function(
				messageJson) {
			var message = JSON.parse(messageJson.body);
			alertUser(message.from, message.text);
		});
	</script>

</sec:authorize>


<%@include file="/WEB-INF/includes/footer.jsp" %>