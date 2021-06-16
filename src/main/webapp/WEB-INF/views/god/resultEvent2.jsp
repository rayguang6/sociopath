<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1> Result Of Event2</h1>


<c:out value="speaker: ${s1}"></c:out><br>
<c:out value="listener:  ${s2}"></c:out><br>
<c:out value="discussed:  ${s3}"></c:out><br><br>

		  	<c:forEach var="result" items="${results}" varStatus="s">
		  		
		  	
		  		<c:out value="${result}"></c:out>
		  		
		 		<br>
		 		
	 		 </c:forEach>

	
	
	<br>
	