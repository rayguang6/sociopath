<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1> Result Of Event 1</h1>


<c:out value="Mentee: ${mentee}"></c:out>
<c:out value="Mentor:  ${mentor}"></c:out>


		  	<c:forEach var="result" items="${results}" varStatus="s">
		  		
		  	
		  		<c:out value="${result}"></c:out>
		  		
		 		<br>
		 		
	 		 </c:forEach>

	
	
	<br>
	<c:out value="This before: ${befores}"></c:out><br>
	<c:out value="This before: ${afters}"></c:out>