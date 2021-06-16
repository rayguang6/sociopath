<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<c:url var="url" value="/viewstatus" />




<div class="statusContainer">
	<div class="mb-3">

		<jwp:pagination url="${url}" page="${page}" size="10" />

		<c:forEach var="statusUpdate" items="${page.content}">
		
			<c:url var="editLink" value="/editstatus?id=${statusUpdate.id}" />
			<c:url var="deleteLink" value="/deletestatus?id=${statusUpdate.id}" />


			<div class="panel panel-default">

				<div class="panel-heading">
					<div class="panel-title">
						Announced On
						<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s "
							value="${statusUpdate.added}" />
					</div>
				</div>

				<div class="panel-body">

					<div>${statusUpdate.text}</div>

					<sec:authorize access="hasRole('ADMIN')">
					<div class="edit-links pull-right">
						<a href="${editLink}">edit</a> | <a onclick="return confirm('Really delete this status update?');" href="${deleteLink}">delete</a>
					</div>
					
					</sec:authorize>

				</div>


			</div>

		</c:forEach>

	</div>
</div>




