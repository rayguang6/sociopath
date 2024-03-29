<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/chatviewscript.jsp" %>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<c:url var="url" value="/messages" />

<c:if test="${empty messageList.content}">
You have no new messages.
</c:if>

<c:forEach var="message" items="${messageList.content}">

	<c:url var="messageUrl" value="/markread?f=${message.fromUserId}&m=${message.id}" />
	
	<div class="message-received">
	
	<fmt:formatDate value="${message.sent}" type="both"/>
	
	 <a href="${messageUrl}">${message.from}</a>
	
	</div>
<p/>

</c:forEach>


<jwp:pagination url="${url}" page="${messageList}" size="10" />




<%@include file="/WEB-INF/includes/footer.jsp" %>