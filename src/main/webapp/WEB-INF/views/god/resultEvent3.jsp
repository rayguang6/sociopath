<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1> Result Of Lunch  </h1>

<br>
<h3>Your Result is</h3>


		  	<c:forEach var="lunchList" items="${lunchLists}" varStatus="s">
		  		
		  	
		  		<c:out value="${lunchList}"></c:out>
		 		<br>
	 		 </c:forEach>

	