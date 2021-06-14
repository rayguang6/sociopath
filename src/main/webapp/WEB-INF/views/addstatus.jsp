<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="statusContainer">
	<div class="mb-3">
		<div class="panel panel-default">

			<div class="panel-heading">
				<h3 class="panel-title statusPostTitle">Post Something Here</h3>
			</div>

			<div class="panel-body">

				<form:form class="statusForm" modelAttribute="statusUpdate">

					<div class="errors">
						<form:errors path="text" />
					</div>
					<div class="form-group">
						<form:textarea class="form-control statusArea" path="text"
							name="text" rows="10" cols="50"></form:textarea>
					</div>

					<input type="submit" class="statusButton" name="submit"
						value="Add Status" />
				</form:form>
			</div>

		</div>


		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title">
					Status update added on
					<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s"
						value="${latestStatusUpdate.added}" />
				</div>
			</div>

			<div class="panel-body">${latestStatusUpdate.text}</div>


		</div>

	</div>
</div>

