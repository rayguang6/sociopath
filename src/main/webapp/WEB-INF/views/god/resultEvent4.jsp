
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h1>Temp Result Of 4  </h1>

<br>
<h3>Your Result is</h3>


<h5>Your Input is : <c:out value="${books}"></c:out></h5>

		  	<c:forEach var="bookResult" items="${bookResults}" varStatus="s">
		  		
		  		<c:out value="Round"></c:out>
		  		<c:out value="${s.index+1} )"></c:out>
		  		<c:out value="${bookResult}"></c:out>
		 		<br>
	 		 </c:forEach>

	